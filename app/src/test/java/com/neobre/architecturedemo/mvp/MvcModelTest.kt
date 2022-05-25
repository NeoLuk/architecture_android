package com.neobre.architecturedemo.mvp

import android.os.Handler
import android.os.Looper
import io.mockk.*
import io.mockk.impl.annotations.RelaxedMockK
import org.junit.Assert
import org.junit.Before
import org.junit.Test

class MvpModelTest {
    private var model: MvpModel = MvpModel()

    @RelaxedMockK
    lateinit var looper: Looper

    @Before
    fun setUp() {
        MockKAnnotations.init(this)
        mockkStatic(Looper::class)
        mockkConstructor(Handler::class)

    }

    @Test
    fun `addRetryCount updates retryCount`() {
        every { Looper.getMainLooper() } returns looper
        every { anyConstructed<Handler>().postDelayed(any(), any()) } answers (
                object : Answer<Boolean> {
                    override fun answer(call: Call): Boolean {
                        (call.invocation.args.first() as Runnable).run()
                        return true
                    }
                })

        model.addRetryCount(null)
        Assert.assertEquals(1, model.retryCount)
    }
}
