package com.neobre.architecturedemo.mvi

sealed class MviIntent {
    object RetryClicked : MviIntent()
}
