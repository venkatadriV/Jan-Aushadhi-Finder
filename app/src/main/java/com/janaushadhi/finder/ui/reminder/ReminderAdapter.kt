package com.janaushadhi.finder.ui.reminder

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.janaushadhi.finder.data.model.Reminder
import com.janaushadhi.finder.databinding.ItemReminderBinding
import com.janaushadhi.finder.util.toDateTimeString

class ReminderAdapter(
    private val onToggleClick: (Reminder, Boolean) -> Unit,
    private val onDeleteClick: (Reminder) -> Unit
) : ListAdapter<Reminder, ReminderAdapter.ReminderViewHolder>(ReminderDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ReminderViewHolder {
        val binding = ItemReminderBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ReminderViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ReminderViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    inner class ReminderViewHolder(private val binding: ItemReminderBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(reminder: Reminder) {
            binding.apply {
                tvMedicineName.text = reminder.medicineName
                tvTime.text = reminder.scheduledTime.toDateTimeString()
                
                // Prevent listener trigger during view recycling
                switchActive.setOnCheckedChangeListener(null)
                switchActive.isChecked = reminder.isActive
                
                switchActive.setOnCheckedChangeListener { _, isChecked ->
                    onToggleClick(reminder, isChecked)
                }
                
                ivDelete.setOnClickListener {
                    onDeleteClick(reminder)
                }
            }
        }
    }

    class ReminderDiffCallback : DiffUtil.ItemCallback<Reminder>() {
        override fun areItemsTheSame(oldItem: Reminder, newItem: Reminder): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Reminder, newItem: Reminder): Boolean {
            return oldItem == newItem
        }
    }
}
