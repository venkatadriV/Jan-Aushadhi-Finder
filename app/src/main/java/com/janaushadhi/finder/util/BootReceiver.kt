package com.janaushadhi.finder.util

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import com.janaushadhi.finder.data.db.AppDatabase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

/**
 * Reschedules all active reminders after device boot.
 */
class BootReceiver : BroadcastReceiver() {
    override fun onReceive(context: Context, intent: Intent) {
        if (intent.action == Intent.ACTION_BOOT_COMPLETED) {
            CoroutineScope(Dispatchers.IO).launch {
                val db = AppDatabase.getDatabase(context)
                val reminders = db.reminderDao().getActiveRemindersList()
                for (reminder in reminders) {
                    if (reminder.scheduledTime > System.currentTimeMillis()) {
                        ReminderReceiver.scheduleReminder(
                            context, reminder.id, reminder.medicineName, reminder.scheduledTime
                        )
                    }
                }
            }
        }
    }
}
