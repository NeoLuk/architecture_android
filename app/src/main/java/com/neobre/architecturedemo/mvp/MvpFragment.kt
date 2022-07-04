package com.neobre.architecturedemo.mvp

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import android.widget.Button
import android.widget.ProgressBar
import android.widget.TextView
import androidx.core.view.isInvisible
import androidx.navigation.fragment.findNavController
import com.neobre.architecturedemo.R
import com.neobre.architecturedemo.util.showRetryLimitDialog


class MvpFragment : Fragment(R.layout.fragment_mvp), MvpContract.View {
    private lateinit var button: Button
    private lateinit var textCount: TextView
    private lateinit var loadingBar: ProgressBar

    private var presenter: MvpContract.Presenter? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        button = view.findViewById(R.id.vRetryBtn)
        textCount = view.findViewById(R.id.vTextCount)
        loadingBar = view.findViewById(R.id.vLoading)

        presenter = MvpPresenter(this).also {
            it.initCount()
        }

        setupView()
    }

    override fun onDestroy() {
        super.onDestroy()
        presenter = null
    }

    private fun setupView() {
        button.setOnClickListener {
            presenter?.onRetryClick()
        }
    }

    override fun setLoading(loading: Boolean) {
        loadingBar.isInvisible = !loading
        button.isEnabled = !loading
    }

    override fun updateCountText(countText: String) {
        textCount.text = countText
    }

    override fun showRetryLimitDialog() {
        requireContext().showRetryLimitDialog { findNavController().popBackStack() }
    }
}
