package com.neobre.architecturedemo.mvp

class MvpPresenter(
    private val view: MvpContract.View,
    private val model: MvpContract.Model = MvpModel()
) : MvpContract.Presenter, MvpContract.Model.OnUpdatedListener {

    override fun onUpdated(count: Int) {
        view.setLoading(false)
        if (count > 3) {
            view.showRetryLimitDialog()
        } else {
            view.updateCountText("count: $count")
        }
    }

    override fun initCount() {
        view.updateCountText("count: ${model.getRetryCount()}")
    }


    override fun onRetryClick() {
        view.setLoading(true)
        model.addRetryCount(this)
    }
}
