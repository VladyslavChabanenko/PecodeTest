package com.chb.pecodetest

import android.app.*
import android.app.PendingIntent.FLAG_IMMUTABLE
import android.content.Context
import android.content.Intent
import android.graphics.BitmapFactory
import android.os.Build
import androidx.core.app.NotificationCompat
import com.chb.pecodetest.Constants.NOTIFICATION_CHANNEL_ID
import com.chb.pecodetest.Constants.NOTIFICATION_CLICKED_KEY

class NotificationHelper(private val context: Context) {

    private lateinit var notificationManager: NotificationManager

    init {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel(
                NOTIFICATION_CHANNEL_ID,
                context.getString(R.string.notification_channel_reminder_name),
                NotificationManager.IMPORTANCE_HIGH
            ).apply {
                lockscreenVisibility = Notification.VISIBILITY_PUBLIC
                notificationManager =
                    context.getSystemService(Application.NOTIFICATION_SERVICE) as NotificationManager
                notificationManager.createNotificationChannel(this)
            }
        }
    }

    private var builder: NotificationCompat.Builder =
        NotificationCompat.Builder(context, NOTIFICATION_CHANNEL_ID)
            .setLargeIcon(
                BitmapFactory.decodeResource(context.resources, R.drawable.ic_notification_large)
            )
            .setSmallIcon(R.drawable.ic_notification_small)
            .setContentTitle(context.getString(R.string.notification_title))
            .setAutoCancel(true)

    fun showNotification(id: Int, message: String) {
        val intent = Intent(context, MainActivity::class.java).apply {
            putExtra(NOTIFICATION_CLICKED_KEY, id)
        }
        val pendingIntent =
            PendingIntent.getActivity(context, id, intent, FLAG_IMMUTABLE)

        val notification = builder
            .setContentText(message)
            .setContentIntent(pendingIntent)
            .build()
        notificationManager.notify(id, notification)
    }

    fun deleteNotification(id: Int) {
        notificationManager.cancel(id)
    }
}