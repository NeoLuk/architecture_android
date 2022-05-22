package com.neobre.architecturedemo.mvvm

import kotlinx.coroutines.delay

class MvvmModel {
    private var retryCount: Int = 0

    suspend fun addRetryCount(): Int {
        delay(500)
        retryCount++
        return retryCount
    }
}
