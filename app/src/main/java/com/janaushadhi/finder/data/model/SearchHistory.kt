package com.janaushadhi.finder.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * SearchHistory entity to cache recent medicine searches.
 * Enables offline access to previously searched medicines.
 */
@Entity(tableName = "search_history")
data class SearchHistory(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,

    /** The search query string */
    val query: String,

    /** Timestamp of the search */
    val searchedAt: Long = System.currentTimeMillis()
)
