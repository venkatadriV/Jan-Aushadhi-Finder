package com.janaushadhi.finder.ui.home

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.janaushadhi.finder.data.model.Medicine
import com.janaushadhi.finder.data.repository.MedicineRepository
import kotlinx.coroutines.launch

class HomeViewModel(application: Application) : AndroidViewModel(application) {
    private val repository = MedicineRepository(application)
    
    private val _totalMedicines = MutableLiveData<Int>()
    val totalMedicines: LiveData<Int> = _totalMedicines

    init {
        viewModelScope.launch {
            _totalMedicines.value = repository.getMedicineCount()
        }
    }
}
