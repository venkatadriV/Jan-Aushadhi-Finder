package com.janaushadhi.finder.data.db

import androidx.lifecycle.LiveData
import androidx.room.*
import com.janaushadhi.finder.data.model.Favorite
import com.janaushadhi.finder.data.model.Medicine

/**
 * Data Access Object for Favorite entity.
 * Manages user's favorite medicines list.
 */
@Dao
interface FavoriteDao {

    /** Get all favorite medicine IDs */
    @Query("SELECT * FROM favorites ORDER BY addedAt DESC")
    fun getAllFavorites(): LiveData<List<Favorite>>

    /** Get all favorite medicines with full details via JOIN */
    @Query("""
        SELECT m.* FROM medicines m 
        INNER JOIN favorites f ON m.id = f.medicineId 
        ORDER BY f.addedAt DESC
    """)
    fun getFavoriteMedicines(): LiveData<List<Medicine>>

    /** Check if a medicine is favorited */
    @Query("SELECT COUNT(*) > 0 FROM favorites WHERE medicineId = :medicineId")
    suspend fun isFavorite(medicineId: Int): Boolean

    /** Check if a medicine is favorited as LiveData */
    @Query("SELECT COUNT(*) > 0 FROM favorites WHERE medicineId = :medicineId")
    fun isFavoriteLive(medicineId: Int): LiveData<Boolean>

    /** Add a medicine to favorites */
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addFavorite(favorite: Favorite)

    /** Remove a medicine from favorites by medicine ID */
    @Query("DELETE FROM favorites WHERE medicineId = :medicineId")
    suspend fun removeFavorite(medicineId: Int)

    /** Get count of favorites */
    @Query("SELECT COUNT(*) FROM favorites")
    suspend fun getFavoriteCount(): Int
}
