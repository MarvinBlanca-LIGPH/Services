package com.example.services.foregroundService

import android.app.*
import android.content.*
import android.os.*
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.core.app.NotificationCompat
import com.example.services.R

class MyForegroundService : Service() {
    private val notificationID = 16
    var start = 0
    private val iBinder: IBinder = MyBinder()
    private var timer: CountDownTimer? = null
    private var callback: ServiceCallback? = null

    inner class MyBinder : Binder() {
        fun getServiceBinder(): MyForegroundService {
            return this@MyForegroundService
        }
    }

    fun setCallback(cb: ServiceCallback?) {
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

    fun startTimer() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            startOwnForeground()
        } else {
            startForeground(notificationID, getNotification())
        }
        timer = object : CountDownTimer(start.toLong(), 1000) {
            override fun onFinish() {
                callback?.onComplete(resources.getString(R.string.times_up))
                stopForeground(true)
            }

            override fun onTick(p0: Long) {
                callback?.onProgress("Timer is at: ${p0 / 1000}")
            }
        }
        timer?.start()
    }

    private fun getNotification(): Notification {
        val intent = Intent(this, ForegroundServiceActivity::class.java)
        val pendingIntent = PendingIntent.getActivity(this, 0, intent, 0)

        return NotificationCompat.Builder(this)
            .setContentTitle(resources.getString(R.string.notification_title))
            .setContentText(resources.getString(R.string.notification_text))
            .setSmallIcon(R.mipmap.ic_launcher_round)
            .setContentIntent(pendingIntent)
            .build()
    }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun startOwnForeground() {
        val notificationChannelId = "com.example.services"
        val channelName = "Services"
        val channel = NotificationChannel(
            notificationChannelId,
            channelName,
            NotificationManager.IMPORTANCE_NONE
        )
        val manager = (getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager)
        manager.createNotificationChannel(channel)
        val notificationBuilder = NotificationCompat.Builder(this, notificationChannelId)
        val notification = notificationBuilder.setOngoing(true)
            .setSmallIcon(R.mipmap.ic_launcher_round)
            .setContentTitle(resources.getString(R.string.notification_title))
            .setContentText(resources.getString(R.string.notification_text))
            .setPriority(NotificationManager.IMPORTANCE_MIN)
            .setCategory(Notification.CATEGORY_SERVICE)
            .build()
        startForeground(2, notification)
    }
}

interface ServiceCallback {
    fun onProgress(message: String)
    fun onComplete(message: String)
    fun onError(error: String)
}