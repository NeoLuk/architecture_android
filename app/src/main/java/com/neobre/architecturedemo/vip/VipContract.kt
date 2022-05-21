package com.neobre.architecturedemo.vip

interface VipContract {
    interface View {
        fun setScore(score: String)
    }

    interface Router {
        fun goToResult()
    }

    interface Presenter {
        fun presentScore(score: String)
    }

    interface Interactor {
        fun loadScore()
    }

    interface Repository {
        fun loadScore()
    }

}