package com.neobre.architecturedemo.mvvm

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class MvvmViewModel(private val repo: MvvmRepository) : ViewModel() {
    private val _count = MutableLiveData(0)
    val count: LiveData<Int>
        get() = _count

    private val _loading = MutableLiveData(false)
    val loading: LiveData<Boolean>
        get() = _loading


    fun onRetryClick() {
        viewModelScope.launch {
            _loading.value = true
            val count = repo.onRetryClick()
            _count.value = count
            _loading.value = false
        }
    }
}
