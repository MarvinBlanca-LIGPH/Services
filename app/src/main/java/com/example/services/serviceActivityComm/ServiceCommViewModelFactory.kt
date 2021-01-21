package com.example.services.serviceActivityComm

import androidx.lifecycle.*

class ServiceCommViewModelFactory(
    private val activity: ServiceCommActivity
) : ViewModelProvider.Factory {
    @Suppress("unchecked_cast")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(ServiceCommViewModel::class.java))
            return ServiceCommViewModel(activity) as T

        throw IllegalArgumentException("Unknown ViewModel Class")
    }
}