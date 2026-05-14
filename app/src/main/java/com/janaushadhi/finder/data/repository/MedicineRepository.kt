package com.janaushadhi.finder.data.repository

import android.app.Application
import androidx.lifecycle.LiveData
import com.janaushadhi.finder.data.db.AppDatabase
import com.janaushadhi.finder.data.model.Favorite
import com.janaushadhi.finder.data.model.Medicine
import com.janaushadhi.finder.data.model.SearchHistory
import com.janaushadhi.finder.util.FuzzySearch

/**
 * Repository for medicine-related data operations.
 * Abstracts data sources from ViewModels.
 */
class MedicineRepository(application: Application) {
    private val db = AppDatabase.getDatabase(application)
    private val medicineDao = db.medicineDao()
    private val favoriteDao = db.favoriteDao()
    private val searchHistoryDao = db.searchHistoryDao()

    val allMedicines: LiveData<List<Medicine>> = medicineDao.getAllMedicines()
    val favoriteMedicines: LiveData<List<Medicine>> = favoriteDao.getFavoriteMedicines()

    fun searchMedicines(query: String): LiveData<List<Medicine>> = medicineDao.searchMedicines(query)
    fun getMedicinesByCategory(category: String): LiveData<List<Medicine>> = medicineDao.getMedicinesByCategory(category)
    fun getEmergencyMedicines(): LiveData<List<Medicine>> = medicineDao.getEmergencyMedicines()
    fun getMedicineByIdLive(id: Int): LiveData<Medicine?> = medicineDao.getMedicineByIdLive(id)
    fun isFavoriteLive(medicineId: Int): LiveData<Boolean> = favoriteDao.isFavoriteLive(medicineId)
    fun getRecentSearches(): LiveData<List<SearchHistory>> = searchHistoryDao.getRecentSearches()

    suspend fun getMedicineById(id: Int): Medicine? = medicineDao.getMedicineById(id)
    suspend fun getAllCategories(): List<String> = medicineDao.getAllCategories()
    suspend fun getMedicineCount(): Int = medicineDao.getMedicineCount()

    /** Fuzzy search that handles spelling mistakes using Levenshtein distance */
    suspend fun fuzzySearchMedicines(query: String): List<Medicine> {
        // First try exact SQL LIKE search
        val exactResults = medicineDao.searchMedicinesList(query)
        if (exactResults.isNotEmpty()) return exactResults

        // Fall back to fuzzy search
        val allMeds = medicineDao.getAllMedicinesList()
        return FuzzySearch.search(query, allMeds)
    }

    suspend fun toggleFavorite(medicineId: Int) {
        if (favoriteDao.isFavorite(medicineId)) {
            favoriteDao.removeFavorite(medicineId)
        } else {
            favoriteDao.addFavorite(Favorite(medicineId = medicineId))
        }
    }

    suspend fun isFavorite(medicineId: Int): Boolean = favoriteDao.isFavorite(medicineId)

    suspend fun saveSearchQuery(query: String) {
        val existing = searchHistoryDao.findByQuery(query)
        if (existing != null) {
            searchHistoryDao.deleteSearch(existing)
        }
        searchHistoryDao.insertSearch(SearchHistory(query = query))
    }

    suspend fun clearSearchHistory() = searchHistoryDao.clearHistory()
}
