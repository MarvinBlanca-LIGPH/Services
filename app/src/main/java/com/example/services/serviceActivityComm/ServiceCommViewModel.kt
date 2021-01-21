package com.example.services.serviceActivityComm

import android.content.Intent
import android.os.*
import androidx.lifecycle.*

class ServiceCommViewModel(
    private val activity: ServiceCommActivity
) : ViewModel() {

    companion object {
        const val resCode = 11
        val handler = Handler()
        private val _text = MutableLiveData<String>()
    }

    val text
        get() = _text

    fun startButton() {
        val intent = Intent(activity, MyIntentService::class.java)
        val receiver: ResultReceiver = MyReceiver(handler)
        intent.putExtra("receiver", receiver)
        activity.startService(intent)
    }

    class MyReceiver(private val handler: Handler) : ResultReceiver(handler) {
        override fun onReceiveResult(resultCode: Int, resultData: Bundle?) {
            if (resultCode == resCode) {
                if (resultData != null) {
                    val msg = resultData.getString("result");
                    handler.post {
                        _text.value = msg
                    }
                }
            }
        }
    }
}
