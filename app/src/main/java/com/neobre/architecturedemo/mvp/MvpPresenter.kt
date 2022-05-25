package com.neobre.architecturedemo.mvp

class MvpPresenter(
    private val view: MvpContract.View,
    private val model: MvpContract.Model = MvpModel()
) : MvpContract.Presenter, MvpContract.Model.OnUpdatedListener {

    override fun onUpdated(count: Int) {
        view.showLoading(false)
        if (count > 3) {
            view.showRetryLimitDialog()
        } else {
            view.updateCountText("count: $count")
        }
    }

    override fun initCount() {
        view.updateCountText("count: ${model.retryCount}")
    }


    override fun onRetryClick() {
        view.showLoading(true)
        model.addRetryCount(this)
    }
}
