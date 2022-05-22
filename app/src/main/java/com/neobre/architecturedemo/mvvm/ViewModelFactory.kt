package com.neobre.architecturedemo.mvvm

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class ViewModelFactory(private val repo: MvvmRepository) : ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MvvmViewModel::class.java)) {
            return MvvmViewModel(repo) as T
        }
        throw IllegalArgumentException("Unknown class name")
    }
}
