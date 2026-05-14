package com.janaushadhi.finder.ui.savings

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.janaushadhi.finder.R
import com.janaushadhi.finder.databinding.FragmentSavingsBinding
import com.janaushadhi.finder.ui.search.MedicineAdapter
import com.janaushadhi.finder.util.toRupee
import com.janaushadhi.finder.util.toRupeeShort

class SavingsFragment : Fragment() {

    private var _binding: FragmentSavingsBinding? = null
    private val binding get() = _binding!!
    private val viewModel: SavingsViewModel by viewModels()
    private lateinit var adapter: MedicineAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSavingsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupRecyclerView()
        setupObservers()
        setupClickListeners()
    }

    private fun setupRecyclerView() {
        adapter = MedicineAdapter(
            onItemClick = { medicine ->
                val bundle = Bundle().apply { putInt("medicineId", medicine.id) }
                findNavController().navigate(R.id.action_savings_to_detail, bundle)
            },
            onFavoriteClick = {
                // In savings calculator, favorite click could remove from list
                // Handled in ViewModel
            }
        )
        binding.rvSavingsMedicines.adapter = adapter
    }

    private fun setupObservers() {
        viewModel.savedMedicines.observe(viewLifecycleOwner) { medicines ->
            adapter.submitList(medicines)
            viewModel.calculateSavings(medicines)
        }
        
        viewModel.totalSavings.observe(viewLifecycleOwner) { savings ->
            binding.tvTotalSavings.text = savings.toRupee()
            binding.tvYearlyProjection.text = "Projected Yearly Savings: ${(savings * 12).toRupeeShort()}"
        }
        
        viewModel.totalBrandPrice.observe(viewLifecycleOwner) { total ->
            binding.tvBrandTotal.text = total.toRupee()
            // Assume Brand price is always 100% of the bar width
            binding.pbBrand.progress = 100
        }
        
        viewModel.totalGenericPrice.observe(viewLifecycleOwner) { total ->
            binding.tvGenericTotal.text = total.toRupee()
            val brandTotal = viewModel.totalBrandPrice.value ?: 1.0
            if (brandTotal > 0) {
                val percentage = ((total / brandTotal) * 100).toInt()
                binding.pbGeneric.progress = percentage
            } else {
                binding.pbGeneric.progress = 0
            }
        }
    }

    private fun setupClickListeners() {
        binding.toolbar.setNavigationOnClickListener {
            findNavController().navigateUp()
        }
        
        binding.btnAddMedicine.setOnClickListener {
            findNavController().navigate(R.id.action_savings_to_search)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
