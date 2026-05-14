package com.janaushadhi.finder.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * Favorite entity to track user's favorite medicines.
 * Links to a Medicine by its ID.
 */
@Entity(tableName = "favorites")
data class Favorite(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,

    /** The ID of the favorited medicine */
    val medicineId: Int,

    /** Timestamp when the medicine was favorited */
    val addedAt: Long = System.currentTimeMillis()
)
