package com.janaushadhi.finder.ui.store

import android.content.Intent
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.janaushadhi.finder.R
import com.janaushadhi.finder.data.model.Store
import com.janaushadhi.finder.databinding.ItemStoreBinding
import com.janaushadhi.finder.util.toKmString

class StoreAdapter(
    private val onCheckStockClick: (Store) -> Unit
) : ListAdapter<Store, StoreAdapter.StoreViewHolder>(StoreDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StoreViewHolder {
        val binding = ItemStoreBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return StoreViewHolder(binding)
    }

    override fun onBindViewHolder(holder: StoreViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    inner class StoreViewHolder(private val binding: ItemStoreBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(store: Store) {
            val context = binding.root.context
            binding.apply {
                tvStoreName.text = store.name
                tvAddress.text = store.address
                tvDistance.text = store.distanceKm.toKmString()
                tvRating.text = store.rating.toString()
                
                if (store.isOpen) {
                    tvOpenStatus.text = context.getString(R.string.store_open)
                    tvOpenStatus.setTextColor(ContextCompat.getColor(context, R.color.store_open))
                } else {
                    tvOpenStatus.text = context.getString(R.string.store_closed)
                    tvOpenStatus.setTextColor(ContextCompat.getColor(context, R.color.store_closed))
                }
                
                // Stock status UI
                when (store.stockStatus) {
                    true -> {
                        tvStockStatus.visibility = View.VISIBLE
                        tvStockStatus.text = context.getString(R.string.store_stock_available)
                        tvStockStatus.setTextColor(ContextCompat.getColor(context, R.color.store_open))
                    }
                    false -> {
                        tvStockStatus.visibility = View.VISIBLE
                        tvStockStatus.text = context.getString(R.string.store_stock_unavailable)
                        tvStockStatus.setTextColor(ContextCompat.getColor(context, R.color.store_closed))
                    }
                    null -> {
                        tvStockStatus.visibility = View.GONE
                    }
                }
                
                btnDirections.setOnClickListener {
                    val gmmIntentUri = Uri.parse("google.navigation:q=${store.latitude},${store.longitude}")
                    val mapIntent = Intent(Intent.ACTION_VIEW, gmmIntentUri)
                    mapIntent.setPackage("com.google.android.apps.maps")
                    if (mapIntent.resolveActivity(context.packageManager) != null) {
                        context.startActivity(mapIntent)
                    }
                }
                
                btnCheckStock.setOnClickListener {
                    onCheckStockClick(store)
                }
            }
        }
    }

    class StoreDiffCallback : DiffUtil.ItemCallback<Store>() {
        override fun areItemsTheSame(oldItem: Store, newItem: Store): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Store, newItem: Store): Boolean {
            return oldItem == newItem
        }
    }
}
