package com.neobre.architecturedemo.mvc

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import android.widget.Button
import android.widget.ProgressBar
import android.widget.TextView
import androidx.navigation.fragment.findNavController
import com.neobre.architecturedemo.R
import com.neobre.architecturedemo.util.showRetryLimitReached
import java.util.*

class MvcFragment : Fragment(R.layout.fragment_mvc), Observer {
    private lateinit var button: Button
    private lateinit var textCount: TextView
    private lateinit var loadingBar: ProgressBar

    private val model = MvcModel()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        button = view.findViewById(R.id.vRetryBtn)
        textCount = view.findViewById(R.id.vTextCount)
        loadingBar = view.findViewById(R.id.vLoading)

        setupView()

        model.addObserver(this)
    }

    private fun setupView() {
        updateCountText(model.retryCount)
        button.setOnClickListener {
            showLoading()
            model.addRetryCount()
        }
    }

    private fun showLoading(loading: Boolean = true) {
        loadingBar.visibility = if (loading) View.VISIBLE else View.INVISIBLE
        button.isEnabled = !loading
    }

    private fun updateCountText(count: Int) {
        // hard to tell where to put this logic.
        textCount.text = "count: $count"
    }

    override fun update(o: Observable?, arg: Any?) {
        if (model.retryCount > 3) return requireContext().showRetryLimitReached { findNavController().popBackStack() }
        updateCountText(model.retryCount)
        showLoading(false)
    }

}
