package com.example.services.callbackService

import androidx.lifecycle.*

class CallbackViewModelFactory(
    private val activity: CallbackServiceActivity
) : ViewModelProvider.Factory {
    @Suppress("unchecked_cast")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(CallbackViewModel::class.java))
            return CallbackViewModel(activity) as T

        throw IllegalArgumentException("Unknown ViewModel Class")
    }
}