package com.neobre.architecturedemo.mvi

sealed class MviViewState {
    object Idle : MviViewState()
    object Loading : MviViewState()
    object ReachLimit : MviViewState()
    data class Count(val count: Int) : MviViewState()
}
