package com.neobre.architecturedemo.mvp

import android.os.Handler
import android.os.Looper
import com.neobre.architecturedemo.mvp.MvpContract

class MvpModel : MvpContract.Model {
    private var retryCount: Int = 0

    override fun getRetryCount(): Int = retryCount

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
