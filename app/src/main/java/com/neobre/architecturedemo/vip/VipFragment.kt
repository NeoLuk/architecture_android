package com.neobre.architecturedemo.vip

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ProgressBar
import android.widget.TextView
import androidx.core.view.isInvisible
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.neobre.architecturedemo.R
import com.neobre.architecturedemo.util.showRetryLimitDialog
import kotlinx.coroutines.launch


class VipFragment : Fragment(R.layout.fragment_vip), VipContract.View {
    private lateinit var button: Button
    private lateinit var textCount: TextView
    private lateinit var loadingBar: ProgressBar

    private lateinit var presenter: VipContract.Presenter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        button = view.findViewById(R.id.vRetryBtn)
        textCount = view.findViewById(R.id.vTextCount)
        loadingBar = view.findViewById(R.id.vLoading)


        presenter = VipPresenter(
            this,
            VipRouter(findNavController()),
        ).also {
            it.initCount()
        }

        setupView()
    }

    private fun setupView() {
        button.setOnClickListener {
            lifecycleScope.launch {
                presenter.onRetryClick()
            }
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
        requireContext().showRetryLimitDialog { presenter.onDialogClick() }
    }
}
