package com.neobre.architecturedemo.mvvm

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import android.widget.Button
import android.widget.ProgressBar
import android.widget.TextView
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.neobre.architecturedemo.R
import com.neobre.architecturedemo.showRetryLimitReached

class MvvmFragment : Fragment(R.layout.fragment_mvvm) {
    private lateinit var button: Button
    private lateinit var textCount: TextView
    private lateinit var loadingBar: ProgressBar

    private val viewModel: MvvmViewModel by viewModels { ViewModelFactory(MvvmRepository(MvvmModel())) }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        button = view.findViewById(R.id.vRetryBtn)
        textCount = view.findViewById(R.id.vTextCount)
        loadingBar = view.findViewById(R.id.vLoading)

        setupView()

        viewModel.uiState.observe(viewLifecycleOwner) {
            if (it.isReachLimit) {
                showRetryLimitDialog()
            } else {
                updateCountText(it.retryCount)
                showLoading(it.loading)
            }
        }
    }


    private fun setupView() {
        button.setOnClickListener {
            viewModel.onRetryClick()
        }
    }

    private fun showLoading(loading: Boolean) {
        loadingBar.visibility = if (loading) View.VISIBLE else View.INVISIBLE
        button.isEnabled = !loading
    }

    private fun updateCountText(countText: String) {
        textCount.text = countText
    }

    private fun showRetryLimitDialog() {
        requireContext().showRetryLimitReached { findNavController().popBackStack() }
    }
}
