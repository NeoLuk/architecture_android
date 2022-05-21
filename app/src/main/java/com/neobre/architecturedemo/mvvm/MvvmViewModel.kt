package com.neobre.architecturedemo.mvvm

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MvvmViewModel(val repo: Int) : ViewModel() {
    private val _retryCount = MutableLiveData(0)
    val retryCount: LiveData<Int>
        get() = _retryCount


    fun onRetryClick() {

    }
}
