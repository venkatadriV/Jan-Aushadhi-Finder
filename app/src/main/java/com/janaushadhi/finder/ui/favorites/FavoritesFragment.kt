package com.janaushadhi.finder.ui.favorites

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.janaushadhi.finder.R
import com.janaushadhi.finder.databinding.FragmentFavoritesBinding
import com.janaushadhi.finder.ui.search.MedicineAdapter

class FavoritesFragment : Fragment() {

    private var _binding: FragmentFavoritesBinding? = null
    private val binding get() = _binding!!
    private val viewModel: FavoritesViewModel by viewModels()
    private lateinit var adapter: MedicineAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentFavoritesBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupRecyclerView()
        setupObservers()
    }

    private fun setupRecyclerView() {
        adapter = MedicineAdapter(
            onItemClick = { medicine ->
                val bundle = Bundle().apply { putInt("medicineId", medicine.id) }
                findNavController().navigate(R.id.action_favorites_to_detail, bundle)
            },
            onFavoriteClick = { medicine ->
                viewModel.toggleFavorite(medicine)
            }
        )
        binding.rvFavorites.adapter = adapter
    }

    private fun setupObservers() {
        viewModel.favoriteMedicines.observe(viewLifecycleOwner) { favorites ->
            adapter.submitList(favorites)
            updateEmptyState(favorites.isEmpty())
        }
    }
    
    private fun updateEmptyState(isEmpty: Boolean) {
        binding.emptyState.visibility = if (isEmpty) View.VISIBLE else View.GONE
        binding.rvFavorites.visibility = if (isEmpty) View.GONE else View.VISIBLE
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
