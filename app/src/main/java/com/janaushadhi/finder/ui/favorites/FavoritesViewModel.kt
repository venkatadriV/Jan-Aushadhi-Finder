package com.janaushadhi.finder.ui.favorites

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.janaushadhi.finder.data.model.Medicine
import com.janaushadhi.finder.data.repository.MedicineRepository
import kotlinx.coroutines.launch

class FavoritesViewModel(application: Application) : AndroidViewModel(application) {
    private val repository = MedicineRepository(application)
    
    val favoriteMedicines: LiveData<List<Medicine>> = repository.favoriteMedicines

    fun toggleFavorite(medicine: Medicine) {
        viewModelScope.launch {
            repository.toggleFavorite(medicine.id)
        }
    }
}
