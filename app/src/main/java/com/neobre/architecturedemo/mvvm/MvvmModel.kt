package com.neobre.architecturedemo.mvvm

import android.os.Handler
import android.os.Looper
import com.neobre.architecturedemo.mvp.MvpContract

class MvvmModel : MvpContract.Model {
    override var retryCount: Int = 0
        private set


    override fun addRetryCount(onUpdatedListener: MvpContract.Model.OnUpdatedListener?) {
        Handler(Looper.getMainLooper()).postDelayed(
            {
                retryCount++
                onUpdatedListener?.onUpdated(retryCount)
            },
            500,
        )
    }
}
