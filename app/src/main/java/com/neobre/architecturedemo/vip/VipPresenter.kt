package com.neobre.architecturedemo.vip

class VipPresenter(
    private val view: VipContract.View,
    private val router: VipContract.Router,
    private val interactor: VipContract.Interactor = VipInteractor()
) : VipContract.Presenter, VipContract.Interactor.OnUpdatedListener {

    override fun onUpdated(count: Int) {
        view.showLoading(false)
        if (count > 3) {
            view.showRetryLimitDialog()
        } else {
            view.updateCountText("count: $count")
        }
    }

    override fun initCount() {
        view.updateCountText("count: 0")
    }

    override suspend fun onRetryClick() {
        view.showLoading(true)
        interactor.addRetryCount(this)
    }

    override fun onDialogClick() = router.back()

}
