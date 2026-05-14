package com.janaushadhi.finder.data.repository

import android.app.Application
import com.janaushadhi.finder.data.db.AppDatabase
import com.janaushadhi.finder.data.model.Reminder

/**
 * Repository for reminder operations.
 */
class ReminderRepository(application: Application) {
    private val reminderDao = AppDatabase.getDatabase(application).reminderDao()

    fun getAllReminders() = reminderDao.getAllReminders()
    fun getActiveReminders() = reminderDao.getActiveReminders()

    suspend fun getActiveRemindersList() = reminderDao.getActiveRemindersList()
    suspend fun getReminderById(id: Int) = reminderDao.getReminderById(id)
    suspend fun insertReminder(reminder: Reminder): Long = reminderDao.insertReminder(reminder)
    suspend fun updateReminder(reminder: Reminder) = reminderDao.updateReminder(reminder)
    suspend fun deleteReminder(reminder: Reminder) = reminderDao.deleteReminder(reminder)
    suspend fun deleteReminderById(id: Int) = reminderDao.deleteReminderById(id)
}
