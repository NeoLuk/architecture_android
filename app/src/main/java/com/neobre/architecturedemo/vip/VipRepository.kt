package com.neobre.architecturedemo.vip

import kotlinx.coroutines.delay

class VipRepository(private val model: VipModel) {
    suspend fun onRetryClick(): Int {
        delay(500)
        model.retryCount++
        return model.retryCount
    }
}
