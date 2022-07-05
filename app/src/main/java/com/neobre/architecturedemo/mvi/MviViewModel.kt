package com.neobre.architecturedemo.mvi

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.neobre.architecturedemo.mvvm.MvvmRepository
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

class MviViewModel(private val repo: MvvmRepository) : ViewModel() {
    private val _viewState = MutableStateFlow<MviViewState>(MviViewState.Init)
    val viewState: StateFlow<MviViewState> = _viewState

    val userIntent = Channel<MviIntent>()

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
