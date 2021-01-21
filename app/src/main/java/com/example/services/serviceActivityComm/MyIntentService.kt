package com.example.services.serviceActivityComm

import android.app.IntentService
import android.content.Intent
import android.os.*


class MyIntentService: IntentService("intent_service") {
    override fun onHandleIntent(intent: Intent?) {
        var index = 1
        val receiver: ResultReceiver ?= intent!!.getParcelableExtra("receiver")
        val b = Bundle()
        b.putString("result", "Counter starts")
        receiver?.send(ServiceCommViewModel.resCode, b)

        while (index <= 15) {
            println("Counter is $index")
            try {
                Thread.sleep(1000)
            } catch (e: InterruptedException){
                e.printStackTrace()
            }
            index++

            b.putString("result","Counter is $index");
            receiver?.send(ServiceCommViewModel.resCode,b)
        }
        b.putString("result","Counter Finished");
        receiver?.send(ServiceCommViewModel.resCode,b)
    }
}