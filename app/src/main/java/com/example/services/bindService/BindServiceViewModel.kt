package com.example.services.bindService

import android.content.*
import android.os.IBinder
import androidx.lifecycle.ViewModel

class BindServiceViewModel(
    private val activity: BindServiceActivity
) : ViewModel() {
    lateinit var intent: Intent
    var myBindService: MyBindService? = null
    var isCalled = false

    private val serviceConn = object : ServiceConnection {
        override fun onServiceConnected(name: ComponentName?, service: IBinder?) {
            myBindService = (service as MyBindService.MyBinder).getServiceBinder()
            myBindService?.start = 16000
            activity.startService(intent)
            isCalled = true
        }

        override fun onServiceDisconnected(name: ComponentName?) {
            myBindService = null
        }

    }

    fun startButton() {
        intent = Intent(activity, MyBindService::class.java)
        activity.bindService(intent, serviceConn, Context.BIND_AUTO_CREATE)
    }

    fun stopButton() {
        if (isCalled) {
            activity.unbindService(serviceConn)
            isCalled = false
        }
    }
}