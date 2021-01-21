package com.example.services.foregroundService

import android.content.*
import android.os.IBinder
import androidx.lifecycle.*

class ForegroundViewModel(
    private val activity: ForegroundServiceActivity
) : ViewModel(), ServiceCallback {
    lateinit var intent: Intent
    var myBindService: MyForegroundService? = null
    var isCalled = false
    private val _message = MutableLiveData<String>()
    val message
        get() = _message
    private val _visibility = MutableLiveData<Boolean>()
    val visibility
        get() = _visibility

    private val serviceConn = object : ServiceConnection {
        override fun onServiceConnected(name: ComponentName?, service: IBinder?) {
            myBindService = (service as MyForegroundService.MyBinder).getServiceBinder()
            myBindService?.start = 16000
            myBindService?.setCallback(this@ForegroundViewModel)
            _visibility.value = true
            isCalled = true
        }

        override fun onServiceDisconnected(name: ComponentName?) {
            myBindService = null
        }
    }

    fun startButton() {
        intent = Intent(activity, MyForegroundService::class.java)
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