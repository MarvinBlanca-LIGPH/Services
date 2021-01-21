package com.example.services.callbackService

import android.content.*
import android.os.IBinder
import androidx.lifecycle.*

class CallbackViewModel(
    private val activity: CallbackServiceActivity
) : ViewModel(), ServiceCallback {
    lateinit var intent: Intent
    var myBindService: MyCallbackService? = null
    var isCalled = false
    private val _message = MutableLiveData<String>()
    val message
        get() = _message
    private val _visibility = MutableLiveData<Boolean>()
    val visibility
        get() = _visibility

    private val serviceConn = object : ServiceConnection {
        override fun onServiceConnected(name: ComponentName?, service: IBinder?) {
            myBindService = (service as MyCallbackService.MyBinder).getServiceBinder()
            myBindService?.start = 16000
            myBindService?.setCallback(this@CallbackViewModel)
            _visibility.value = true
            isCalled = true
        }

        override fun onServiceDisconnected(name: ComponentName?) {
            myBindService = null
        }
    }

    fun startButton() {
        intent = Intent(activity, MyCallbackService::class.java)
        activity.bindService(intent, serviceConn, Context.BIND_AUTO_CREATE)
    }

    fun stopButton() {
        if (isCalled) {
            myBindService?.setCallback(null)
            activity.unbindService(serviceConn)
            _visibility.value = false
            isCalled = false
        }
    }

    fun startTimer() {
        myBindService?.let {
            myBindService?.startTimer()
        }
    }

    override fun onProgress(message: String) {
        _message.value = message
    }

    override fun onComplete(message: String) {
        _message.value = message
    }

    override fun onError(error: String) {
        _message.value = error
    }
}