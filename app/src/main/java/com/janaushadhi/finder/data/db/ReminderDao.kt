package com.janaushadhi.finder.data.db

import androidx.lifecycle.LiveData
import androidx.room.*
import com.janaushadhi.finder.data.model.Reminder

/**
 * Data Access Object for Reminder entity.
 * Manages medicine refill reminders.
 */
@Dao
interface ReminderDao {

    /** Get all reminders ordered by scheduled time */
    @Query("SELECT * FROM reminders ORDER BY scheduledTime ASC")
    fun getAllReminders(): LiveData<List<Reminder>>

    /** Get all active reminders */
    @Query("SELECT * FROM reminders WHERE isActive = 1 ORDER BY scheduledTime ASC")
    fun getActiveReminders(): LiveData<List<Reminder>>

    /** Get all active reminders as list (for rescheduling after boot) */
    @Query("SELECT * FROM reminders WHERE isActive = 1")
    suspend fun getActiveRemindersList(): List<Reminder>

    /** Get a reminder by ID */
    @Query("SELECT * FROM reminders WHERE id = :id")
    suspend fun getReminderById(id: Int): Reminder?

    /** Insert a new reminder */
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertReminder(reminder: Reminder): Long

    /** Update an existing reminder */
    @Update
    suspend fun updateReminder(reminder: Reminder)

    /** Delete a reminder */
    @Delete
    suspend fun deleteReminder(reminder: Reminder)

    /** Delete a reminder by ID */
    @Query("DELETE FROM reminders WHERE id = :id")
    suspend fun deleteReminderById(id: Int)
}
