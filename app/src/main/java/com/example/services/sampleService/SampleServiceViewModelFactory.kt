package com.example.services.sampleService

import androidx.lifecycle.*

class SampleServiceViewModelFactory(
    private val activity: SampleServiceActivity
) : ViewModelProvider.Factory {
    @Suppress("unchecked_cast")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(SampleServiceViewModel::class.java)) {
            return SampleServiceViewModel(activity) as T
        }
        throw IllegalArgumentException("Unknown ViewModel Class")
    }
}