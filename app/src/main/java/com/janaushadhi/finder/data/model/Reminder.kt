package com.janaushadhi.finder.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * Reminder entity for medicine refill reminders.
 * Stores the alarm details for scheduling notifications.
 */
@Entity(tableName = "reminders")
data class Reminder(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,

    /** Name of the medicine to remind about */
    val medicineName: String,

    /** Scheduled time in milliseconds (epoch) */
    val scheduledTime: Long,

    /** Frequency: "daily", "weekly", or "monthly" */
    val frequency: String = "monthly",

    /** Optional notes for the reminder */
    val notes: String = "",

    /** Whether the reminder is currently active */
    val isActive: Boolean = true,

    /** Timestamp when the reminder was created */
    val createdAt: Long = System.currentTimeMillis()
)
