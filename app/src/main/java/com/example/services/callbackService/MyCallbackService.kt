package com.example.services.callbackService

import android.app.Service
import android.content.Intent
import android.os.*
import android.widget.Toast
import com.example.services.R

class MyCallbackService : Service() {
    var start = 0
    private val iBinder: IBinder = MyBinder()
    private var timer: CountDownTimer? = null
    private var callback: ServiceCallback? = null

    inner class MyBinder : Binder() {
        fun getServiceBinder(): MyCallbackService {
            return this@MyCallbackService
        }
    }

    fun setCallback(cb: ServiceCallback?){
        callback = cb
    }

    override fun onBind(intent: Intent?): IBinder? {
        Toast.makeText(this, "onBind Called", Toast.LENGTH_SHORT).show()
        return iBinder
    }

    override fun onDestroy() {
        super.onDestroy()
        timer?.cancel()
        Toast.makeText(this, "onDestroy Called", Toast.LENGTH_SHORT).show()
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        return START_REDELIVER_INTENT
    }

    fun startTimer(){
        timer = object : CountDownTimer(start.toLong(), 1000) {
            override fun onFinish() {
                callback?.onComplete(resources.getString(R.string.times_up))
            }

            override fun onTick(p0: Long) {
                callback?.onProgress("Timer is at: ${p0 / 1000}")
            }
        }
        timer?.start()
    }
}

interface ServiceCallback {
    fun onProgress(message: String)
    fun onComplete(message: String)
    fun onError(error: String)
}