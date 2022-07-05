package com.neobre.architecturedemo.mvi

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import android.widget.Button
import android.widget.ProgressBar
import android.widget.TextView
import androidx.core.view.isInvisible
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.neobre.architecturedemo.R
import com.neobre.architecturedemo.mvvm.MvvmDataSource
import com.neobre.architecturedemo.mvvm.MvvmRepository
import com.neobre.architecturedemo.util.ViewModelFactory
import com.neobre.architecturedemo.util.showRetryLimitDialog
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class MviFragment : Fragment(R.layout.fragment_mvi) {
    private lateinit var button: Button
    private lateinit var textCount: TextView
    private lateinit var loadingBar: ProgressBar

    private val viewModel: MviViewModel by viewModels { ViewModelFactory(MvvmRepository(MvvmDataSource())) }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        button = view.findViewById(R.id.vRetryBtn)
        textCount = view.findViewById(R.id.vTextCount)
        loadingBar = view.findViewById(R.id.vLoading)

        setupView()
        observeViewState()
    }

    private fun setupView() {
        button.setOnClickListener {
            lifecycleScope.launch {
                viewModel.userIntent.send(MviIntent.RetryClicked)
            }
        }
    }

    private fun setLoading(loading: Boolean) {
        loadingBar.isInvisible = !loading
        button.isEnabled = !loading
    }

    private fun updateCountText(count: Int) {
        textCount.text = getString(R.string.count_text, count)
    }

    private fun observeViewState() {
        lifecycleScope.launch {
            viewModel.viewState.collect {
                when (it) {
                    is MviViewState.Count -> {
                        setLoading(false)
                        updateCountText(it.count)
                    }
                    is MviViewState.Init -> {
                        updateCountText(0)
                    }
                    is MviViewState.Loading -> {
                        setLoading(true)
                    }
                    is MviViewState.ReachLimit -> {
                        setLoading(false)
                        requireContext().showRetryLimitDialog { findNavController().popBackStack() }
                    }
                }
            }
        }
    }
}
