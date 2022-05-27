package com.neobre.architecturedemo.vip

import kotlinx.coroutines.delay


class VipInteractor(private val model: VipModel = VipModel()) : VipContract.Interactor {

    override suspend fun addRetryCount(onUpdatedListener: VipContract.Interactor.OnUpdatedListener?) {
        delay(500)
        model.retryCount++
        onUpdatedListener?.onUpdated(model.retryCount)
    }
}
