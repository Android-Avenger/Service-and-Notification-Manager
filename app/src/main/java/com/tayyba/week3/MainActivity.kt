package com.tayyba.week3

import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.graphics.BitmapFactory
import android.graphics.Color
import android.os.Build
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.NotificationCompat

class MainActivity : AppCompatActivity() {

    lateinit var manager: NotificationManager
    lateinit var channel: NotificationChannel
    lateinit var builder: NotificationCompat.Builder
    private val channelId = "i.apps.notifications"
    private val description = "Test notification"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val btn = findViewById<Button>(R.id.ClickMe)

        manager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

        btn.setOnClickListener {

            createNotification()
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {

                channel =
                    NotificationChannel(channelId, description, NotificationManager.IMPORTANCE_HIGH)
                channel.enableLights(true)
                channel.lockscreenVisibility = 1
                channel.lightColor = Color.GREEN
                channel.enableVibration(true)

                manager.createNotificationChannel(channel)

                channel.vibrationPattern = longArrayOf(100, 200, 300, 400, 500, 400, 300, 200, 400)

                builder =  NotificationCompat.Builder(this,channelId)
                    .setContentText("Notification")
                    .setStyle(
                        NotificationCompat.BigTextStyle()
                            .bigText("MMuch longer text that cannot fit one line..Much longer text that cannot fit one line..uch longer text that cannot fit one line..."))
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
        }
    }

    private fun createNotification() {

    }
}
