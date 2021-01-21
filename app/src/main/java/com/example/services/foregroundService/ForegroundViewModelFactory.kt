package com.example.services.foregroundService

import androidx.lifecycle.*

class ForegroundViewModelFactory(
    private val activity: ForegroundServiceActivity
) : ViewModelProvider.Factory {
    @Suppress("unchecked_cast")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(ForegroundViewModel::class.java))
            return ForegroundViewModel(activity) as T

        throw IllegalArgumentException("Unknown ViewModel Class")
    }
}