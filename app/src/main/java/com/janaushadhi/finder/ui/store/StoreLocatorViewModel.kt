package com.janaushadhi.finder.ui.store

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.janaushadhi.finder.data.model.Store
import com.janaushadhi.finder.data.repository.FirestoreStoreRepository
import com.janaushadhi.finder.data.repository.StoreRepository
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class StoreLocatorViewModel : ViewModel() {
    private val localRepository = StoreRepository()
    private val firestoreRepository = FirestoreStoreRepository()
    
    private val _stores = MutableLiveData<List<Store>>()
    val stores: LiveData<List<Store>> = _stores

    /**
     * Loads stores from Firestore first, falls back to local data.
     */
    fun loadStoresNearLocation(lat: Double, lng: Double, radiusKm: Double = 50.0) {
        viewModelScope.launch {
            try {
                // Try Firestore first, it automatically falls back to local
                val nearbyStores = firestoreRepository.getNearbyStores(lat, lng, radiusKm)
                _stores.value = nearbyStores
            } catch (e: Exception) {
                Log.w("StoreLocatorVM", "Firestore failed, using local: ${e.message}")
                val nearbyStores = localRepository.getNearbyStores(lat, lng, radiusKm)
                _stores.value = nearbyStores
            }
        }
    }
    
    fun checkStock(store: Store, medicineName: String, onResult: (Boolean) -> Unit) {
        viewModelScope.launch {
            // Simulate network delay
            delay(1500)
            val isAvailable = localRepository.checkStock(store.id, medicineName)
            
            // Update store list
            val currentList = _stores.value?.toMutableList() ?: return@launch
            val index = currentList.indexOfFirst { it.id == store.id }
            if (index != -1) {
                currentList[index] = store.copy(stockStatus = isAvailable)
                _stores.value = currentList
            }
            
            onResult(isAvailable)
        }
    }
}
