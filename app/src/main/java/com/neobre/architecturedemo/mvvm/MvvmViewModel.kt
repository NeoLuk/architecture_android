package com.neobre.architecturedemo.mvvm

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class MvvmViewModel(private val repo: MvvmRepository) : ViewModel() {
    private val _uiState = MutableLiveData(MvvmUIState(
        retryCount = "count: 0",
        loading = false,
        isReachLimit = false
    ))
    val uiState: LiveData<MvvmUIState>
        get() = _uiState



    fun onRetryClick() {
        _uiState.value = _uiState.value?.copy(loading = true)
        viewModelScope.launch {
            val count =  repo.onRetryClick()
            _uiState.value = MvvmUIState(
                loading = false,
                retryCount = "count: $count",
                isReachLimit = count > 3
            )
        }
    }
}

data class MvvmUIState(
    val retryCount: String,
    val loading: Boolean,
    val isReachLimit: Boolean
)
