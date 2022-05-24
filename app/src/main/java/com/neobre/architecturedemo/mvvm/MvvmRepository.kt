package com.neobre.architecturedemo.mvvm

import kotlinx.coroutines.delay

class MvvmRepository(private val model: MvvmModel) {
    suspend fun onRetryClick(): Int {
        delay(500)
        model.retryCount++
        return model.retryCount
    }
}
