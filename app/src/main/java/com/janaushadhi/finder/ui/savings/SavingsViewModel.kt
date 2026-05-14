package com.janaushadhi.finder.ui.savings

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.janaushadhi.finder.data.model.Medicine
import com.janaushadhi.finder.data.repository.MedicineRepository

class SavingsViewModel(application: Application) : AndroidViewModel(application) {
    private val repository = MedicineRepository(application)
    
    // We'll use favorite medicines as the base for the savings calculator
    // in a real app, this might be a separate "cart" or "prescription" list
    val savedMedicines: LiveData<List<Medicine>> = repository.favoriteMedicines
    
    private val _totalBrandPrice = MutableLiveData<Double>(0.0)
    val totalBrandPrice: LiveData<Double> = _totalBrandPrice
    
    private val _totalGenericPrice = MutableLiveData<Double>(0.0)
    val totalGenericPrice: LiveData<Double> = _totalGenericPrice
    
    private val _totalSavings = MutableLiveData<Double>(0.0)
    val totalSavings: LiveData<Double> = _totalSavings
    
    private val _savingsPercentage = MutableLiveData<Double>(0.0)
    val savingsPercentage: LiveData<Double> = _savingsPercentage

    fun calculateSavings(medicines: List<Medicine>) {
        var brandTotal = 0.0
        var genericTotal = 0.0
        
        for (med in medicines) {
            brandTotal += med.brandPrice
            genericTotal += med.genericPrice
        }
        
        _totalBrandPrice.value = brandTotal
        _totalGenericPrice.value = genericTotal
        _totalSavings.value = brandTotal - genericTotal
        
        val percent = if (brandTotal > 0) ((brandTotal - genericTotal) / brandTotal) * 100 else 0.0
        _savingsPercentage.value = percent
    }
}
