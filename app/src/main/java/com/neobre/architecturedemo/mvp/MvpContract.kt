package com.neobre.architecturedemo.mvp

interface MvpContract {
    interface View {
        fun updateCountText(countText: String)
        fun showRetryLimitDialog()
        fun showLoading(loading: Boolean)
    }

    interface Model {
        val retryCount: Int

        interface OnUpdatedListener {
            fun onUpdated(count: Int)
        }

        fun addRetryCount(onUpdatedListener: OnUpdatedListener?)
    }

    interface Presenter {
        fun onRetryClick()
        fun initCount()
    }
}
