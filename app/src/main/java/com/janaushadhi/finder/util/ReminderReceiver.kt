package com.janaushadhi.finder.util

import android.app.AlarmManager
import android.app.PendingIntent
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.os.Build

/**
 * BroadcastReceiver that fires when a medicine reminder alarm triggers.
 */
class ReminderReceiver : BroadcastReceiver() {
    override fun onReceive(context: Context, intent: Intent) {
        val reminderId = intent.getIntExtra("reminder_id", 0)
        val medicineName = intent.getStringExtra("medicine_name") ?: "Your medicine"
        NotificationHelper.showReminderNotification(context, reminderId, medicineName)
    }

    companion object {
        /** Schedule an alarm for a reminder */
        fun scheduleReminder(context: Context, reminderId: Int, medicineName: String, timeInMillis: Long) {
            val intent = Intent(context, ReminderReceiver::class.java).apply {
                putExtra("reminder_id", reminderId)
                putExtra("medicine_name", medicineName)
            }
            val pendingIntent = PendingIntent.getBroadcast(
                context, reminderId, intent,
                PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE
            )
            val alarmManager = context.getSystemService(Context.ALARM_SERVICE) as AlarmManager
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
                if (alarmManager.canScheduleExactAlarms()) {
                    alarmManager.setExactAndAllowWhileIdle(AlarmManager.RTC_WAKEUP, timeInMillis, pendingIntent)
                } else {
                    alarmManager.setAndAllowWhileIdle(AlarmManager.RTC_WAKEUP, timeInMillis, pendingIntent)
                }
            } else {
                alarmManager.setExactAndAllowWhileIdle(AlarmManager.RTC_WAKEUP, timeInMillis, pendingIntent)
            }
        }

        /** Cancel a scheduled reminder */
        fun cancelReminder(context: Context, reminderId: Int) {
            val intent = Intent(context, ReminderReceiver::class.java)
            val pendingIntent = PendingIntent.getBroadcast(
                context, reminderId, intent,
                PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE
            )
            val alarmManager = context.getSystemService(Context.ALARM_SERVICE) as AlarmManager
            alarmManager.cancel(pendingIntent)
        }
    }
}
