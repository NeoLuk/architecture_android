package com.neobre.architecturedemo.mvi

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.neobre.architecturedemo.mvvm.MvvmRepository
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.consumeAsFlow
import kotlinx.coroutines.launch

class MviViewModel(private val repo: MvvmRepository) : ViewModel() {
    private val _viewState = MutableStateFlow<MviViewState>(MviViewState.Idle)
    val viewState: StateFlow<MviViewState>
        get() = _viewState

    val userIntent = Channel<MviIntent>(Channel.UNLIMITED)

    init {
        viewModelScope.launch {
            userIntent.consumeAsFlow().collect {
                when (it) {
                    MviIntent.RetryClicked -> onRetryClick()
                }
            }
        }
    }


    private fun onRetryClick() {
        viewModelScope.launch {
            _viewState.value = MviViewState.Loading
            val count = repo.onRetryClick()
            if (count > 3) {
                _viewState.value = MviViewState.ReachLimit
            } else {
                _viewState.value = MviViewState.Count(count)
            }
        }
    }
}
