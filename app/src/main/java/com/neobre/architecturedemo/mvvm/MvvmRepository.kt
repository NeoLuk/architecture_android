package com.neobre.architecturedemo.mvvm

class MvvmRepository(private val model: MvvmModel) {
    suspend fun onRetryClick(): Int = model.addRetryCount()
}
