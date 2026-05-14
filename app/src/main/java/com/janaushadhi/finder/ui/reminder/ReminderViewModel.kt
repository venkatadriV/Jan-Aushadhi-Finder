package com.janaushadhi.finder.ui.reminder

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.janaushadhi.finder.data.model.Reminder
import com.janaushadhi.finder.data.repository.ReminderRepository
import com.janaushadhi.finder.util.ReminderReceiver
import kotlinx.coroutines.launch

class ReminderViewModel(application: Application) : AndroidViewModel(application) {
    private val repository = ReminderRepository(application)
    
    val allReminders: LiveData<List<Reminder>> = repository.getAllReminders()

    fun addReminder(medicineName: String, timeInMillis: Long) {
        viewModelScope.launch {
            val reminder = Reminder(
                medicineName = medicineName,
                scheduledTime = timeInMillis,
                isActive = true
            )
            val id = repository.insertReminder(reminder)
            ReminderReceiver.scheduleReminder(getApplication(), id.toInt(), medicineName, timeInMillis)
        }
    }

    fun toggleReminder(reminder: Reminder, isChecked: Boolean) {
        viewModelScope.launch {
            val updatedReminder = reminder.copy(isActive = isChecked)
            repository.updateReminder(updatedReminder)
            
            if (isChecked) {
                ReminderReceiver.scheduleReminder(getApplication(), reminder.id, reminder.medicineName, reminder.scheduledTime)
            } else {
                ReminderReceiver.cancelReminder(getApplication(), reminder.id)
            }
        }
    }

    fun deleteReminder(reminder: Reminder) {
        viewModelScope.launch {
            repository.deleteReminder(reminder)
            ReminderReceiver.cancelReminder(getApplication(), reminder.id)
        }
    }
}
