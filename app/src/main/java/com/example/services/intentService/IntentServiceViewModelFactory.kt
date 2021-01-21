package com.example.services.intentService

import androidx.lifecycle.*

class IntentServiceViewModelFactory(
    private val activity: IntentServiceActivity
) : ViewModelProvider.Factory {
    @Suppress("unchecked_cast")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(IntentServiceViewModel::class.java)) {
            return IntentServiceViewModel(activity) as T
        }
        throw IllegalArgumentException("Unknown ViewModel Class")
    }
}