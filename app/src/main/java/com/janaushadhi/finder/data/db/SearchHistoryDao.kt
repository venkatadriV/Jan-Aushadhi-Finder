package com.janaushadhi.finder.data.db

import androidx.lifecycle.LiveData
import androidx.room.*
import com.janaushadhi.finder.data.model.SearchHistory

/**
 * Data Access Object for SearchHistory entity.
 * Caches recent searches for offline access.
 */
@Dao
interface SearchHistoryDao {

    /** Get recent searches, latest first, limited to 20 */
    @Query("SELECT * FROM search_history ORDER BY searchedAt DESC LIMIT 20")
    fun getRecentSearches(): LiveData<List<SearchHistory>>

    /** Insert a search query */
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertSearch(search: SearchHistory)

    /** Delete all search history */
    @Query("DELETE FROM search_history")
    suspend fun clearHistory()

    /** Delete a specific search entry */
    @Delete
    suspend fun deleteSearch(search: SearchHistory)

    /** Check if a query already exists and update timestamp */
    @Query("SELECT * FROM search_history WHERE query = :query LIMIT 1")
    suspend fun findByQuery(query: String): SearchHistory?
}
