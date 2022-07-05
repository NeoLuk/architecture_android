package com.neobre.architecturedemo.mvvm

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class MvvmViewModel(private val repo: MvvmRepository) : ViewModel() {
    private val _count = MutableLiveData(0)
    val count: LiveData<Int> = _count

    // better use Channel and SharedFlow in real project for one time events.
    private val _loading = MutableLiveData(false)
    val loading: LiveData<Boolean> = _loading


    fun onRetryClick() {
        viewModelScope.launch {
            _loading.value = true
            _count.value = repo.onRetryClick()
            _loading.value = false
        }
    }
}
