package com.neobre.architecturedemo.mvp.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import android.widget.Button
import android.widget.ProgressBar
import android.widget.TextView
import androidx.navigation.fragment.findNavController
import com.neobre.architecturedemo.R
import com.neobre.architecturedemo.mvp.MvpContract
import com.neobre.architecturedemo.mvp.presenter.MvpPresenter
import com.neobre.architecturedemo.util.showRetryLimitReached


class MvpFragment : Fragment(R.layout.fragment_mvp), MvpContract.View {
    private lateinit var button: Button
    private lateinit var textCount: TextView
    private lateinit var loadingBar: ProgressBar

    private lateinit var presenter: MvpContract.Presenter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        button = view.findViewById(R.id.vRetryBtn)
        textCount = view.findViewById(R.id.vTextCount)
        loadingBar = view.findViewById(R.id.vLoading)

        presenter = MvpPresenter(this)

        setupView()
    }

    private fun setupView() {
        presenter.initCount()
        button.setOnClickListener {
            presenter.onRetryClick()
        }
    }

    override fun showLoading(loading: Boolean) {
        loadingBar.visibility = if (loading) View.VISIBLE else View.INVISIBLE
        button.isEnabled = !loading
    }

    override fun updateCountText(countText: String) {
        textCount.text = countText
    }

    override fun showRetryLimitDialog() {
        requireContext().showRetryLimitReached { findNavController().popBackStack() }
    }
}
