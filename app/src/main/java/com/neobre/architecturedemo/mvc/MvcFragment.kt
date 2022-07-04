package com.neobre.architecturedemo.mvc

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import android.widget.Button
import android.widget.ProgressBar
import android.widget.TextView
import androidx.core.view.isInvisible
import androidx.core.view.isVisible
import androidx.navigation.fragment.findNavController
import com.neobre.architecturedemo.R
import com.neobre.architecturedemo.util.showRetryLimitDialog
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

    override fun onDestroy() {
        super.onDestroy()
        model.deleteObservers()
    }

    private fun setupView() {
        updateCountText(model.retryCount)
        button.setOnClickListener {
            setLoading(true)
            model.addRetryCount()
        }
    }

    private fun setLoading(loading: Boolean) {
        loadingBar.isInvisible = !loading
        button.isEnabled = !loading
    }

    private fun updateCountText(count: Int) {
        textCount.text = getString(R.string.count_text, count)
    }

    override fun update(o: Observable?, arg: Any?) {
        if (model.retryCount > 3) return requireContext().showRetryLimitDialog { findNavController().popBackStack() }
        updateCountText(model.retryCount)
        setLoading(false)
    }

}
