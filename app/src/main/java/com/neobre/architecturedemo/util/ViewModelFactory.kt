package com.neobre.architecturedemo.util

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.neobre.architecturedemo.mvi.MviViewModel
import com.neobre.architecturedemo.mvvm.MvvmRepository
import com.neobre.architecturedemo.mvvm.MvvmViewModel

class ViewModelFactory(private val repo: MvvmRepository) : ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MvvmViewModel::class.java)) {
            return MvvmViewModel(repo) as T
        } else if (modelClass.isAssignableFrom(MviViewModel::class.java)) {
            return MviViewModel(repo) as T
        }
        throw IllegalArgumentException("Unknown class name")
    }
}
