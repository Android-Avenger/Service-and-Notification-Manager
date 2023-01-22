package com.tayyba.week3

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.Service
import android.content.Context
import android.content.Intent
import android.graphics.BitmapFactory
import android.graphics.Color
import android.os.Build
import android.os.IBinder
import android.view.LayoutInflater
import android.widget.RemoteViews
import androidx.core.app.NotificationCompat

class MyService : Service() {

    lateinit var manager: NotificationManager
    lateinit var channel: NotificationChannel
    lateinit var builder: NotificationCompat.Builder
    private val channelId = "i.apps.notifications"
    private val description = "Test notification"

    override fun onCreate() {
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        manager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {

            val inflater = getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
            val customView = inflater.inflate(R.layout.custom_notification, null)
            val remoteView = RemoteViews(packageName,R.layout.custom_notification)
            channel = NotificationChannel(channelId, description, NotificationManager.IMPORTANCE_HIGH)
            channel.enableLights(true)
            channel.lockscreenVisibility = 1
            channel.lightColor = Color.GREEN
            channel.enableVibration(true)

            manager.createNotificationChannel(channel)

            channel.vibrationPattern = longArrayOf(100, 200, 300, 400, 500, 400, 300, 200, 400)
            builder =  NotificationCompat.Builder(this,channelId)
                .setAutoCancel(false)
                .setContent(remoteView)
                .setColor(Color.BLUE)
                .setOngoing(true)
//                .setStyle(
//                    NotificationCompat.BigTextStyle()
//                        .bigText("MMuch longer text that cannot fit one line..Much longer text that cannot fit one line..uch longer text that cannot fit one line..."))
                .setSmallIcon(R.drawable.ic_launcher_foreground)
                .setLargeIcon(
                    BitmapFactory.decodeResource(
                        this.resources,
                        R.drawable.ic_baseline_ac_unit_24
                    ))
        } else {

            builder = NotificationCompat.Builder(this)
                .setContentText("Simple")
                .setSmallIcon(R.drawable.ic_launcher_foreground)
                .setLargeIcon(
                    BitmapFactory.decodeResource(
                        this.resources,
                        R.drawable.ic_baseline_ac_unit_24
                    )
                )

        }

        manager.notify(1234, builder.build())
        return START_STICKY
    }
    override fun onBind(p0: Intent?): IBinder? {
        TODO("Not yet implemented")
    }

    override fun onDestroy() {
        super.onDestroy()
    }
}