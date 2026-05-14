package com.janaushadhi.finder.ui.search

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.janaushadhi.finder.data.model.Medicine
import com.janaushadhi.finder.databinding.ItemMedicineBinding
import com.janaushadhi.finder.util.toPercent
import com.janaushadhi.finder.util.toRupee

class MedicineAdapter(
    private val onItemClick: (Medicine) -> Unit,
    private val onFavoriteClick: (Medicine) -> Unit
) : ListAdapter<Medicine, MedicineAdapter.MedicineViewHolder>(MedicineDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MedicineViewHolder {
        val binding = ItemMedicineBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MedicineViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MedicineViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    inner class MedicineViewHolder(private val binding: ItemMedicineBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(medicine: Medicine) {
            binding.apply {
                tvBrandName.text = medicine.brandName
                tvGenericName.text = medicine.genericName
                tvSaltComposition.text = medicine.saltComposition
                tvBrandPrice.text = medicine.brandPrice.toRupee()
                tvGenericPrice.text = medicine.genericPrice.toRupee()
                tvSavingsPercent.text = medicine.savingsPercentage.toPercent()
                
                // Favorite icon toggle based on state could be done here if state passed, but 
                // for simplicity we just pass click event
                
                root.setOnClickListener { onItemClick(medicine) }
                ivFavorite.setOnClickListener { onFavoriteClick(medicine) }
            }
        }
    }

    class MedicineDiffCallback : DiffUtil.ItemCallback<Medicine>() {
        override fun areItemsTheSame(oldItem: Medicine, newItem: Medicine): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Medicine, newItem: Medicine): Boolean {
            return oldItem == newItem
        }
    }
}
