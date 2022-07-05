package com.neobre.architecturedemo.mvvm


class MvvmRepository(private val model: MvvmDataSource) {
    suspend fun onRetryClick(): Int {
        return model.addRetryCount()
    }
}
