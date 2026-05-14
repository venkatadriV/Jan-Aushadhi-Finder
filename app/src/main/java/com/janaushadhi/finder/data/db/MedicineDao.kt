package com.janaushadhi.finder.data.db

import androidx.lifecycle.LiveData
import androidx.room.*
import com.janaushadhi.finder.data.model.Medicine

/**
 * Data Access Object for Medicine entity.
 * Provides all database operations for medicine records.
 */
@Dao
interface MedicineDao {

    /** Get all medicines ordered by brand name */
    @Query("SELECT * FROM medicines ORDER BY brandName ASC")
    fun getAllMedicines(): LiveData<List<Medicine>>

    /** Get all medicines as a plain list (for search/filter operations) */
    @Query("SELECT * FROM medicines ORDER BY brandName ASC")
    suspend fun getAllMedicinesList(): List<Medicine>

    /** Get a single medicine by its ID */
    @Query("SELECT * FROM medicines WHERE id = :id")
    suspend fun getMedicineById(id: Int): Medicine?

    /** Get a single medicine by ID as LiveData */
    @Query("SELECT * FROM medicines WHERE id = :id")
    fun getMedicineByIdLive(id: Int): LiveData<Medicine?>

    /** Search medicines by brand name, generic name, or salt composition */
    @Query("""
        SELECT * FROM medicines 
        WHERE brandName LIKE '%' || :query || '%' 
        OR genericName LIKE '%' || :query || '%' 
        OR saltComposition LIKE '%' || :query || '%'
        ORDER BY brandName ASC
    """)
    fun searchMedicines(query: String): LiveData<List<Medicine>>

    /** Search medicines synchronously for fuzzy search processing */
    @Query("""
        SELECT * FROM medicines 
        WHERE brandName LIKE '%' || :query || '%' 
        OR genericName LIKE '%' || :query || '%' 
        OR saltComposition LIKE '%' || :query || '%'
        ORDER BY brandName ASC
    """)
    suspend fun searchMedicinesList(query: String): List<Medicine>

    /** Get medicines by category */
    @Query("SELECT * FROM medicines WHERE category = :category ORDER BY brandName ASC")
    fun getMedicinesByCategory(category: String): LiveData<List<Medicine>>

    /** Get emergency medicines only */
    @Query("SELECT * FROM medicines WHERE isEmergency = 1 ORDER BY brandName ASC")
    fun getEmergencyMedicines(): LiveData<List<Medicine>>

    /** Get all distinct categories */
    @Query("SELECT DISTINCT category FROM medicines ORDER BY category ASC")
    suspend fun getAllCategories(): List<String>

    /** Get total number of medicines */
    @Query("SELECT COUNT(*) FROM medicines")
    suspend fun getMedicineCount(): Int

    /** Insert a single medicine */
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertMedicine(medicine: Medicine)

    /** Insert multiple medicines at once */
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(medicines: List<Medicine>)

    /** Delete a medicine */
    @Delete
    suspend fun deleteMedicine(medicine: Medicine)
}
