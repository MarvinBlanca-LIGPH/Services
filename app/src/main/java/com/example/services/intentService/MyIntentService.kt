package com.example.services.intentService

import android.app.IntentService
import android.content.Intent

class MyIntentService: IntentService("intent_service") {
    override fun onHandleIntent(intent: Intent?) {
        var index = 1
        while (index <= 15) {
            println("Counter is $index")
            try {
                Thread.sleep(1000)
            } catch (e: InterruptedException){
                e.printStackTrace()
            }
            index++
        }
    }
}