package com.example.services.intentService

import android.content.Intent
import androidx.lifecycle.ViewModel

class IntentServiceViewModel(
    private val activity: IntentServiceActivity
): ViewModel() {

    fun startButton() {
        val intent = Intent(activity, MyIntentService::class.java)
        activity.startService(intent)
    }
}