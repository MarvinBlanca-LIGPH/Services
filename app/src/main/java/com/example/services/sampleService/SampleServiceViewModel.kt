package com.example.services.sampleService

import android.content.Intent
import androidx.lifecycle.ViewModel

class SampleServiceViewModel(
    private val activity: SampleServiceActivity
) : ViewModel() {

    fun startButton() {
        val intent = Intent(activity, MyService::class.java)
        activity.startService(intent)
    }

    fun stopButton() {
        val intent = Intent(activity, MyService::class.java)
        activity.stopService(intent)
    }
}