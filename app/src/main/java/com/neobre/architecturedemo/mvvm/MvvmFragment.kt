package com.neobre.architecturedemo.mvvm

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
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

    private val viewModel: MvvmViewModel by viewModels { ViewModelFactory(1) }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        button = view.findViewById(R.id.vRetryBtn)
        textCount = view.findViewById(R.id.vTextCount)
        loadingBar = view.findViewById(R.id.vLoading)

        setupView()

        viewModel.retryCount.observe(viewLifecycleOwner) {

        }
    }


    private fun setupView() {
//        presenter.initCount()
        button.setOnClickListener {
            viewModel.onRetryClick()
        }
    }

    fun showLoading(loading: Boolean) {
        loadingBar.visibility = if (loading) View.VISIBLE else View.INVISIBLE
        button.isEnabled = !loading
    }

    fun updateCountText(countText: String) {
        textCount.text = countText
    }

    fun showRetryLimitDialog() {
        requireContext().showRetryLimitReached { findNavController().popBackStack() }
    }
}
