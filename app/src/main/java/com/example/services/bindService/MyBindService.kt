package com.example.services.bindService

import android.app.Service
import android.content.Intent
import android.os.*
import android.widget.Toast
import com.example.services.R

class MyBindService : Service() {
    var start = 0
    private val iBinder: IBinder = MyBinder()
    lateinit var timer: CountDownTimer

    inner class MyBinder : Binder() {
        fun getServiceBinder(): MyBindService {
            return this@MyBindService
        }
    }

    override fun onBind(intent: Intent?): IBinder? {
        Toast.makeText(this, "onBind Called", Toast.LENGTH_SHORT).show()
        return iBinder
    }

    override fun onDestroy() {
        super.onDestroy()
        timer.cancel()
        Toast.makeText(this, "onDestroy Called", Toast.LENGTH_SHORT).show()
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        timer = object : CountDownTimer(start.toLong(), 1000) {
            override fun onFinish() {
                Toast.makeText(
                    this@MyBindService,
                    resources.getString(R.string.times_up),
                    Toast.LENGTH_SHORT
                ).show()
                stopSelf()
            }

            override fun onTick(p0: Long) {
                Toast.makeText(this@MyBindService, "${p0 / 1000}", Toast.LENGTH_SHORT).show()
            }
        }
        timer.start()

        return START_REDELIVER_INTENT
    }
}