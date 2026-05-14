package com.janaushadhi.finder.ui.reminder

import android.Manifest
import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AlertDialog
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.janaushadhi.finder.R
import com.janaushadhi.finder.databinding.FragmentReminderBinding
import java.util.*

class ReminderFragment : Fragment() {

    private var _binding: FragmentReminderBinding? = null
    private val binding get() = _binding!!
    private val viewModel: ReminderViewModel by viewModels()
    private lateinit var adapter: ReminderAdapter

    private val requestPermissionLauncher = registerForActivityResult(
        ActivityResultContracts.RequestPermission()
    ) { isGranted ->
        if (isGranted) {
            showAddReminderDialog()
        } else {
            Toast.makeText(requireContext(), "Notification permission needed for reminders", Toast.LENGTH_SHORT).show()
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentReminderBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupRecyclerView()
        setupObservers()

        binding.fabAddReminder.setOnClickListener {
            checkPermissionsAndAdd()
        }
    }

    private fun setupRecyclerView() {
        adapter = ReminderAdapter(
            onToggleClick = { reminder, isChecked ->
                viewModel.toggleReminder(reminder, isChecked)
            },
            onDeleteClick = { reminder ->
                viewModel.deleteReminder(reminder)
                Toast.makeText(requireContext(), "Reminder deleted", Toast.LENGTH_SHORT).show()
            }
        )
        binding.rvReminders.adapter = adapter
    }

    private fun setupObservers() {
        viewModel.allReminders.observe(viewLifecycleOwner) { reminders ->
            adapter.submitList(reminders)
            binding.emptyState.visibility = if (reminders.isEmpty()) View.VISIBLE else View.GONE
            binding.rvReminders.visibility = if (reminders.isEmpty()) View.GONE else View.VISIBLE
        }
    }

    private fun checkPermissionsAndAdd() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            if (ContextCompat.checkSelfPermission(
                    requireContext(), Manifest.permission.POST_NOTIFICATIONS
                ) == PackageManager.PERMISSION_GRANTED) {
                showAddReminderDialog()
            } else {
                requestPermissionLauncher.launch(Manifest.permission.POST_NOTIFICATIONS)
            }
        } else {
            showAddReminderDialog()
        }
    }

    private fun showAddReminderDialog() {
        val builder = AlertDialog.Builder(requireContext())
        
        val input = EditText(requireContext())
        input.hint = "Enter medicine name"
        
        builder.setTitle("Add Reminder")
            .setView(input)
            .setPositiveButton("Set Time") { _, _ ->
                val medName = input.text.toString()
                if (medName.isNotEmpty()) {
                    showDateTimePicker(medName)
                }
            }
            .setNegativeButton("Cancel", null)
            .show()
    }
    
    private fun showDateTimePicker(medicineName: String) {
        val calendar = Calendar.getInstance()
        
        DatePickerDialog(requireContext(), { _, year, month, dayOfMonth ->
            calendar.set(Calendar.YEAR, year)
            calendar.set(Calendar.MONTH, month)
            calendar.set(Calendar.DAY_OF_MONTH, dayOfMonth)
            
            TimePickerDialog(requireContext(), { _, hourOfDay, minute ->
                calendar.set(Calendar.HOUR_OF_DAY, hourOfDay)
                calendar.set(Calendar.MINUTE, minute)
                calendar.set(Calendar.SECOND, 0)
                
                if (calendar.timeInMillis > System.currentTimeMillis()) {
                    viewModel.addReminder(medicineName, calendar.timeInMillis)
                    Toast.makeText(requireContext(), "Reminder saved", Toast.LENGTH_SHORT).show()
                } else {
                    Toast.makeText(requireContext(), "Please select a future time", Toast.LENGTH_SHORT).show()
                }
            }, calendar.get(Calendar.HOUR_OF_DAY), calendar.get(Calendar.MINUTE), false).show()
            
        }, calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH)).show()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
