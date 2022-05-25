package com.neobre.architecturedemo.mvp.presenter

import com.neobre.architecturedemo.mvp.MvpContract
import com.neobre.architecturedemo.mvp.MvpPresenter
import io.mockk.*
import io.mockk.impl.annotations.RelaxedMockK

import org.junit.Before
import org.junit.Test

class MvpPresenterTest {

    @RelaxedMockK
    lateinit var view: MvpContract.View

    @RelaxedMockK
    lateinit var model: MvpContract.Model

    lateinit var presenter: MvpPresenter

    @Before
    fun setUp() {
        MockKAnnotations.init(this)
        presenter = MvpPresenter(view, model)
    }

    @Test
    fun `onUpdated success`() {
        every { view.showLoading(any()) } just runs
        every { model.addRetryCount(any()) } just runs

    }

    @Test
    fun onUpdated() {
        every { view.showLoading(any()) } just runs
        every { model.addRetryCount(any()) } just runs

    }

    @Test
    fun initCount() {
        every { model.retryCount } returns 1
        every { view.updateCountText(any()) } just runs


        presenter.initCount()

        verifySequence {
            model.getProperty("retryCount")
            view.updateCountText("count: 1")
        }
    }

    @Test
    fun onRetryClick() {
        every { view.showLoading(any()) } just runs
        every { model.addRetryCount(any()) } just runs

        presenter.onRetryClick()

        verifySequence {
            view.showLoading(true)
            model.addRetryCount(presenter)
        }
    }
}
