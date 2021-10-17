package com.moataz.afternoonhadeeth.ui.notification

import android.annotation.SuppressLint
import android.app.*
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.os.Build
import androidx.core.app.NotificationCompat
import com.moataz.afternoonhadeeth.R
import com.moataz.afternoonhadeeth.data.source.HadithSource
import com.moataz.afternoonhadeeth.ui.view.activity.MainActivity
import java.util.*

class NotificationAfternoon: BroadcastReceiver() {
    private val CHANNEL_ID = "HADITH_AFTERNOON_CHANNEL_ID"

    override fun onReceive(context: Context, intent: Intent?) {
        val notificationIntent = Intent(context, MainActivity::class.java)
        val stackBuilder = TaskStackBuilder.create(context)
        stackBuilder.addParentStack(MainActivity::class.java)
        stackBuilder.addNextIntent(notificationIntent)
        val pendingIntent = stackBuilder.getPendingIntent(0, PendingIntent.FLAG_UPDATE_CURRENT)
        val builder = Notification.Builder(context)
        val notification: Notification = NotificationCompat.Builder(context, CHANNEL_ID)
            .setContentTitle("كنز اليوم")
            .setContentText(HadithSource().firstHadith())
            .setSmallIcon(R.drawable.ic_notification)
            .setLights(Notification.FLAG_SHOW_LIGHTS, 1000, 500)
            .setDefaults(NotificationCompat.DEFAULT_ALL)
            .setPriority(NotificationCompat.PRIORITY_HIGH)
            .setAutoCancel(true)
            .setContentIntent(pendingIntent)
            .setStyle(NotificationCompat.BigTextStyle())
            .build()
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            builder.setChannelId(CHANNEL_ID)
        }
        val notificationManager =
            context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channel = NotificationChannel(
                CHANNEL_ID,
                "Channel_HADITH_Afternoon_ID",
                NotificationManager.IMPORTANCE_HIGH
            )
            channel.enableLights(true)
            channel.lightColor = Color.WHITE
            channel.enableVibration(false)
            notificationManager.createNotificationChannel(channel)
        }
        notificationManager.notify(0, notification)
    }

    fun setupAfternoonNotification(context: Context) {
        val cal = Calendar.getInstance()
        cal.timeInMillis = System.currentTimeMillis()
        cal[Calendar.HOUR_OF_DAY] = 17
        cal[Calendar.MINUTE] = 0
        cal[Calendar.SECOND] = 0
        if (cal.timeInMillis > System.currentTimeMillis()) {
            val notificationIntent = Intent(context, NotificationAfternoon::class.java)
            @SuppressLint("UnspecifiedImmutableFlag") val broadcast = PendingIntent.getBroadcast(
                context,
                0,
                notificationIntent,
                PendingIntent.FLAG_UPDATE_CURRENT
            )
            val alarmManager = context.getSystemService(Context.ALARM_SERVICE) as AlarmManager
            alarmManager.setInexactRepeating(
                AlarmManager.RTC_WAKEUP,
                cal.timeInMillis,
                (24 * 60 * 60 * 1000).toLong(),
                broadcast
            ) //Repeat every 24 h
        }
    }
}