package com.neobre.architecturedemo.mvp

interface MvpContract {
    interface View {
        fun updateCountText(countText: String)
        fun showRetryLimitDialog()
        fun setLoading(loading: Boolean)
    }

    interface Model {
        interface OnUpdatedListener {
            fun onUpdated(count: Int)
        }

        fun getRetryCount(): Int
        fun addRetryCount(onUpdatedListener: OnUpdatedListener?)
    }

    interface Presenter {
        fun onRetryClick()
        fun initCount()
    }
}
