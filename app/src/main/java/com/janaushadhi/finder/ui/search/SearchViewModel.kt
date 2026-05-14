package com.janaushadhi.finder.ui.search

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.janaushadhi.finder.data.model.Medicine
import com.janaushadhi.finder.data.repository.MedicineRepository
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class SearchViewModel(application: Application) : AndroidViewModel(application) {
    private val repository = MedicineRepository(application)
    
    val allMedicines: LiveData<List<Medicine>> = repository.allMedicines
    
    private val _searchResults = MutableLiveData<List<Medicine>>()
    val searchResults: LiveData<List<Medicine>> = _searchResults
    
    private val _isSearching = MutableLiveData<Boolean>(false)
    val isSearching: LiveData<Boolean> = _isSearching
    
    private var searchJob: Job? = null

    fun searchMedicines(query: String) {
        searchJob?.cancel()
        if (query.isEmpty()) {
            _searchResults.value = allMedicines.value ?: emptyList()
            return
        }
        
        searchJob = viewModelScope.launch {
            _isSearching.value = true
            delay(300) // Debounce
            val results = repository.fuzzySearchMedicines(query)
            _searchResults.value = results
            _isSearching.value = false
        }
    }

    fun filterByCategory(category: String) {
        if (category == "All") {
            _searchResults.value = allMedicines.value ?: emptyList()
        } else {
            viewModelScope.launch {
                repository.getMedicinesByCategory(category).observeForever {
                    _searchResults.value = it
                }
            }
        }
    }
    
    fun filterEmergency() {
        viewModelScope.launch {
            repository.getEmergencyMedicines().observeForever {
                _searchResults.value = it
            }
        }
    }

    fun toggleFavorite(medicine: Medicine) {
        viewModelScope.launch {
            repository.toggleFavorite(medicine.id)
        }
    }
}
