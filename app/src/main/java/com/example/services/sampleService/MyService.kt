package com.example.services.sampleService

import android.app.Service
import android.content.Intent
import android.os.*
import android.widget.Toast
import com.example.services.R

class MyService : Service() {
    lateinit var timer: CountDownTimer

    override fun onDestroy() {
        super.onDestroy()
        timer.cancel()
        Toast.makeText(this, "onDestroy Called", Toast.LENGTH_SHORT).show()
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        timer = object : CountDownTimer(16000, 1000) {
            override fun onFinish() {
                Toast.makeText(
                    applicationContext,
                    resources.getString(R.string.times_up),
                    Toast.LENGTH_SHORT
                ).show()
                stopSelf()
            }

            override fun onTick(p0: Long) {
                Toast.makeText(applicationContext, "${p0 / 1000}", Toast.LENGTH_SHORT).show()
            }
        }
        timer.start()

        return START_REDELIVER_INTENT
    }

    override fun onBind(intent: Intent?): IBinder? {
        return null
    }
}