package com.example.services.bindService

import androidx.lifecycle.*

class BindServiceViewModelFactory(
    private val activity: BindServiceActivity
) : ViewModelProvider.Factory {
    @Suppress("unchecked_cast")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(BindServiceViewModel::class.java))
            return BindServiceViewModel(activity) as T

        throw IllegalArgumentException("Unknown ViewModel Class")
    }
}