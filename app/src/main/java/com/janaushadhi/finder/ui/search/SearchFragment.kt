package com.janaushadhi.finder.ui.search

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.google.android.material.chip.Chip
import com.janaushadhi.finder.R
import com.janaushadhi.finder.databinding.FragmentSearchBinding

class SearchFragment : Fragment() {

    private var _binding: FragmentSearchBinding? = null
    private val binding get() = _binding!!
    private val viewModel: SearchViewModel by viewModels()
    private lateinit var adapter: MedicineAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSearchBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupRecyclerView()
        setupSearch()
        setupObservers()
        setupCategoryChips()

        // Check if opened with category filter
        arguments?.getString("category")?.let { category ->
            if (category == "Emergency") {
                viewModel.filterEmergency()
                // Update UI to reflect category selection...
            }
        }
    }

    private fun setupRecyclerView() {
        adapter = MedicineAdapter(
            onItemClick = { medicine ->
                val bundle = Bundle().apply { putInt("medicineId", medicine.id) }
                findNavController().navigate(R.id.action_search_to_detail, bundle)
            },
            onFavoriteClick = { medicine ->
                viewModel.toggleFavorite(medicine)
            }
        )
        binding.rvMedicines.adapter = adapter
    }

    private fun setupSearch() {
        binding.etSearch.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                val query = s.toString()
                binding.ivClearSearch.visibility = if (query.isNotEmpty()) View.VISIBLE else View.GONE
                viewModel.searchMedicines(query)
            }
            override fun afterTextChanged(s: Editable?) {}
        })

        binding.ivClearSearch.setOnClickListener {
            binding.etSearch.text?.clear()
        }
    }

    private fun setupCategoryChips() {
        val categories = listOf("All", "Pain Relief", "Antibiotics", "Diabetes", "Cardiac", "GI", "Respiratory", "Dermatology", "Vitamins", "Allergy")
        
        binding.chipGroupCategories.removeAllViews()
        
        categories.forEach { category ->
            val chip = layoutInflater.inflate(R.layout.item_chip, binding.chipGroupCategories, false) as Chip
            chip.text = category
            chip.isCheckable = true
            if (category == "All") chip.isChecked = true
            
            chip.setOnCheckedChangeListener { _, isChecked ->
                if (isChecked) {
                    viewModel.filterByCategory(category)
                }
            }
            binding.chipGroupCategories.addView(chip)
        }
    }

    private fun setupObservers() {
        viewModel.allMedicines.observe(viewLifecycleOwner) { medicines ->
            if (binding.etSearch.text.isNullOrEmpty()) {
                adapter.submitList(medicines)
                updateEmptyState(medicines.isEmpty())
            }
        }
        
        viewModel.searchResults.observe(viewLifecycleOwner) { results ->
            adapter.submitList(results)
            updateEmptyState(results.isEmpty())
        }
    }
    
    private fun updateEmptyState(isEmpty: Boolean) {
        binding.emptyState.visibility = if (isEmpty) View.VISIBLE else View.GONE
        binding.rvMedicines.visibility = if (isEmpty) View.GONE else View.VISIBLE
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
