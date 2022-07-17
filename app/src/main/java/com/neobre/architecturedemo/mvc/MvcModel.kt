package com.neobre.architecturedemo.mvc

import android.os.Handler
import android.os.Looper
import java.util.*


class MvcModel : Observable() {
    var retryCount: Int = 0
        private set

    fun isRetryBlocked(): Boolean = retryCount > 3

    fun addRetryCount() {
        // simulate api call
        Handler(Looper.getMainLooper()).postDelayed(
            {
                retryCount++
                setChanged()
                notifyObservers()
            },
            500,
        )
    }

    fun initCount() {
        setChanged()
        notifyObservers()
    }

}
