package com.neobre.architecturedemo.mvvm

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.withContext

class MvvmDataSource {
    private var retryCount: Int = 0

    suspend fun addRetryCount(): Int {
        return withContext(Dispatchers.IO){
            delay(500)
            ++retryCount
        }
    }
}
