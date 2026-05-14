package com.janaushadhi.finder.ui.detail

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.janaushadhi.finder.data.model.Medicine
import com.janaushadhi.finder.data.model.Reminder
import com.janaushadhi.finder.data.repository.MedicineRepository
import com.janaushadhi.finder.data.repository.ReminderRepository
import com.janaushadhi.finder.util.ReminderReceiver
import kotlinx.coroutines.launch

class MedicineDetailViewModel(application: Application) : AndroidViewModel(application) {
    private val repository = MedicineRepository(application)
    private val reminderRepository = ReminderRepository(application)
    
    private val _medicine = MutableLiveData<Medicine>()
    val medicine: LiveData<Medicine> = _medicine
    
    private val _isFavorite = MutableLiveData<Boolean>()
    val isFavorite: LiveData<Boolean> = _isFavorite
    
    private val _reminderSet = MutableLiveData<Boolean>()
    val reminderSet: LiveData<Boolean> = _reminderSet

    fun loadMedicine(id: Int) {
        viewModelScope.launch {
            repository.getMedicineById(id)?.let {
                _medicine.value = it
            }
            _isFavorite.value = repository.isFavorite(id)
        }
    }

    fun toggleFavorite() {
        val currentMedicine = _medicine.value ?: return
        viewModelScope.launch {
            repository.toggleFavorite(currentMedicine.id)
            _isFavorite.value = repository.isFavorite(currentMedicine.id)
        }
    }
    
    fun setReminder(timeInMillis: Long) {
        val currentMedicine = _medicine.value ?: return
        viewModelScope.launch {
            val reminder = Reminder(
                medicineName = "${currentMedicine.brandName} (${currentMedicine.genericName})",
                scheduledTime = timeInMillis,
                isActive = true
            )
            val id = reminderRepository.insertReminder(reminder)
            ReminderReceiver.scheduleReminder(
                getApplication(),
                id.toInt(),
                currentMedicine.brandName,
                timeInMillis
            )
            _reminderSet.value = true
        }
    }
}
