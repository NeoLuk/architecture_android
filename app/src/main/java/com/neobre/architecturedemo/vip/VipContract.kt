package com.neobre.architecturedemo.vip


interface VipContract {
    interface View {
        fun setLoading(loading: Boolean)
        fun updateCountText(countText: String)
        fun showRetryLimitDialog()
    }

    interface Presenter {
        fun initCount()
        suspend fun onRetryClick()
        fun onDialogClick()
    }

    interface Interactor {
        interface OnUpdatedListener {
            fun onUpdated(count: Int)
        }

        suspend fun addRetryCount(onUpdatedListener: OnUpdatedListener?)
    }

    interface Router {
        fun back()
    }
}
