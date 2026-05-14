package com.janaushadhi.finder.ui.detail

import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.janaushadhi.finder.R
import com.janaushadhi.finder.data.model.Medicine
import com.janaushadhi.finder.databinding.FragmentMedicineDetailBinding
import com.janaushadhi.finder.util.toPercent
import com.janaushadhi.finder.util.toRupee
import java.util.*

class MedicineDetailFragment : Fragment() {

    private var _binding: FragmentMedicineDetailBinding? = null
    private val binding get() = _binding!!
    private val viewModel: MedicineDetailViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMedicineDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        
        val medicineId = arguments?.getInt("medicineId") ?: return
        viewModel.loadMedicine(medicineId)

        setupObservers()
        setupClickListeners()
    }

    private fun setupObservers() {
        viewModel.medicine.observe(viewLifecycleOwner) { medicine ->
            binding.apply {
                tvBrandName.text = medicine.brandName
                tvGenericName.text = medicine.genericName
                tvSaltComposition.text = "Composition: ${medicine.saltComposition}"
                tvCategory.text = medicine.category
                
                tvBrandPrice.text = medicine.brandPrice.toRupee()
                tvGenericPrice.text = medicine.genericPrice.toRupee()
                
                tvTotalSavings.text = "${medicine.savings.toRupee()} (${medicine.savingsPercentage.toPercent()})"
                
                tvManufacturer.text = medicine.manufacturer.ifEmpty { "Unknown" }
                tvDescription.text = medicine.description.ifEmpty { "No description available." }
            }
        }
        
        viewModel.isFavorite.observe(viewLifecycleOwner) { isFavorite ->
            if (isFavorite) {
                binding.btnFavorite.setIconResource(R.drawable.ic_favorite_filled)
                binding.btnFavorite.text = "Favorited"
            } else {
                binding.btnFavorite.setIconResource(R.drawable.ic_favorite_outline)
                binding.btnFavorite.text = "Favorite"
            }
        }
        
        viewModel.reminderSet.observe(viewLifecycleOwner) { isSet ->
            if (isSet) {
                Toast.makeText(requireContext(), "Reminder saved!", Toast.LENGTH_SHORT).show()
            }
        }
    }
    
    private fun setupClickListeners() {
        binding.toolbar.setNavigationOnClickListener {
            findNavController().navigateUp()
        }
        
        binding.btnFavorite.setOnClickListener {
            viewModel.toggleFavorite()
        }
        
        binding.btnFindStore.setOnClickListener {
            findNavController().navigate(R.id.action_detail_to_stores)
        }
        
        binding.btnSetReminder.setOnClickListener {
            showDateTimePicker()
        }
        
        binding.btnShare.setOnClickListener {
            shareMedicine()
        }
    }
    
    private fun showDateTimePicker() {
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
                    viewModel.setReminder(calendar.timeInMillis)
                } else {
                    Toast.makeText(requireContext(), "Please select a future time", Toast.LENGTH_SHORT).show()
                }
            }, calendar.get(Calendar.HOUR_OF_DAY), calendar.get(Calendar.MINUTE), false).show()
            
        }, calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH)).show()
    }
    
    private fun shareMedicine() {
        val medicine = viewModel.medicine.value ?: return
        val shareText = buildString {
            append("💊 ${medicine.brandName} (${medicine.genericName})\n\n")
            append("🧪 Composition: ${medicine.saltComposition}\n")
            append("💰 Brand Price: ${medicine.brandPrice.toRupee()}\n")
            append("✅ Generic Price: ${medicine.genericPrice.toRupee()}\n")
            append("🎉 You Save: ${medicine.savings.toRupee()} (${medicine.savingsPercentage.toPercent()})\n\n")
            append("Find affordable generic medicines at Jan-Aushadhi Kendras near you!\n")
            append("#JanAushadhi #AffordableHealthcare #PMBJP")
        }
        
        val shareIntent = Intent().apply {
            action = Intent.ACTION_SEND
            type = "text/plain"
            putExtra(Intent.EXTRA_TEXT, shareText)
            putExtra(Intent.EXTRA_SUBJECT, "Save on ${medicine.brandName} with Jan-Aushadhi")
        }
        startActivity(Intent.createChooser(shareIntent, "Share medicine info via"))
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
