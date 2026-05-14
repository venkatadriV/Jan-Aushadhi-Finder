package com.janaushadhi.finder.ui.home

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.janaushadhi.finder.R
import com.janaushadhi.finder.databinding.FragmentHomeBinding

class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!
    private val viewModel: HomeViewModel by viewModels()

    private val facts by lazy {
        listOf(
            getString(R.string.home_fact_1),
            getString(R.string.home_fact_2),
            getString(R.string.home_fact_3)
        )
    }
    private var currentFactIndex = 0
    private val factHandler = Handler(Looper.getMainLooper())
    private val factRotator = object : Runnable {
        override fun run() {
            currentFactIndex = (currentFactIndex + 1) % facts.size
            val fadeIn = AnimationUtils.loadAnimation(requireContext(), R.anim.fade_in)
            binding.tvFact.text = facts[currentFactIndex]
            binding.tvFact.startAnimation(fadeIn)
            factHandler.postDelayed(this, 5000)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        
        setupClickListeners()
        startFactRotation()
    }

    private fun setupClickListeners() {
        binding.searchBarContainer.setOnClickListener {
            findNavController().navigate(R.id.action_home_to_search)
        }

        binding.actionMedicines.setOnClickListener {
            findNavController().navigate(R.id.action_home_to_search)
        }

        binding.actionStores.setOnClickListener {
            findNavController().navigate(R.id.action_home_to_stores)
        }

        binding.actionSavings.setOnClickListener {
            findNavController().navigate(R.id.action_home_to_savings)
        }

        binding.actionFavorites.setOnClickListener {
            findNavController().navigate(R.id.action_home_to_favorites)
        }

        binding.actionEmergency.setOnClickListener {
            val bundle = Bundle().apply {
                putString("category", "Emergency")
            }
            findNavController().navigate(R.id.action_home_to_search, bundle)
        }

        binding.actionReminders.setOnClickListener {
            // Assume we can navigate to reminders from home or bottom nav
            findNavController().navigate(R.id.nav_reminders)
        }
    }

    private fun startFactRotation() {
        binding.tvFact.text = facts[0]
        factHandler.postDelayed(factRotator, 5000)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        factHandler.removeCallbacks(factRotator)
        _binding = null
    }
}
