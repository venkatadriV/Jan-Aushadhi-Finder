package com.janaushadhi.finder.ui.store

import android.Manifest
import android.content.pm.PackageManager
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.janaushadhi.finder.R
import com.janaushadhi.finder.databinding.FragmentStoreLocatorBinding

class StoreLocatorFragment : Fragment(), OnMapReadyCallback {

    private var _binding: FragmentStoreLocatorBinding? = null
    private val binding get() = _binding!!
    private val viewModel: StoreLocatorViewModel by viewModels()
    private lateinit var adapter: StoreAdapter
    
    private var map: GoogleMap? = null
    private lateinit var fusedLocationClient: FusedLocationProviderClient
    
    // Default to New Delhi coordinates if location permission denied
    private val defaultLocation = LatLng(28.6139, 77.2090)

    private val requestPermissionLauncher = registerForActivityResult(
        ActivityResultContracts.RequestPermission()
    ) { isGranted ->
        if (isGranted) {
            enableMyLocation()
        } else {
            showPermissionDeniedState()
            loadStores(defaultLocation)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentStoreLocatorBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        
        fusedLocationClient = LocationServices.getFusedLocationProviderClient(requireActivity())
        
        val mapFragment = childFragmentManager.findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)
        
        setupRecyclerView()
        setupObservers()
        
        binding.btnEnableLocation.setOnClickListener {
            checkLocationPermission()
        }
    }

    private fun setupRecyclerView() {
        adapter = StoreAdapter(
            onCheckStockClick = { store ->
                binding.progressBar.visibility = View.VISIBLE
                viewModel.checkStock(store, "Medicine") { isAvailable ->
                    binding.progressBar.visibility = View.GONE
                    val msg = if (isAvailable) "Stock available!" else "Out of stock."
                    Toast.makeText(requireContext(), msg, Toast.LENGTH_SHORT).show()
                }
            }
        )
        binding.rvStores.adapter = adapter
    }

    private fun setupObservers() {
        viewModel.stores.observe(viewLifecycleOwner) { stores ->
            adapter.submitList(stores)
            updateMapMarkers(stores)
        }
    }

    override fun onMapReady(googleMap: GoogleMap) {
        map = googleMap
        checkLocationPermission()
    }

    private fun checkLocationPermission() {
        when {
            ContextCompat.checkSelfPermission(
                requireContext(), Manifest.permission.ACCESS_FINE_LOCATION
            ) == PackageManager.PERMISSION_GRANTED -> {
                enableMyLocation()
            }
            else -> {
                requestPermissionLauncher.launch(Manifest.permission.ACCESS_FINE_LOCATION)
            }
        }
    }

    private fun enableMyLocation() {
        if (ContextCompat.checkSelfPermission(
                requireContext(), Manifest.permission.ACCESS_FINE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED) return

        map?.isMyLocationEnabled = true
        binding.llPermissionDenied.visibility = View.GONE
        binding.map.visibility = View.VISIBLE
        binding.rvStores.visibility = View.VISIBLE

        fusedLocationClient.lastLocation.addOnSuccessListener { location ->
            if (location != null) {
                val currentLatLng = LatLng(location.latitude, location.longitude)
                map?.moveCamera(CameraUpdateFactory.newLatLngZoom(currentLatLng, 12f))
                loadStores(currentLatLng)
            } else {
                // Fallback
                loadStores(defaultLocation)
                map?.moveCamera(CameraUpdateFactory.newLatLngZoom(defaultLocation, 10f))
            }
        }
    }

    private fun loadStores(location: LatLng) {
        viewModel.loadStoresNearLocation(location.latitude, location.longitude)
    }

    private fun updateMapMarkers(stores: List<com.janaushadhi.finder.data.model.Store>) {
        map?.clear()
        for (store in stores) {
            val position = LatLng(store.latitude, store.longitude)
            map?.addMarker(
                MarkerOptions()
                    .position(position)
                    .title(store.name)
                    .snippet(store.distanceKm.toString() + " km")
            )
        }
    }

    private fun showPermissionDeniedState() {
        binding.llPermissionDenied.visibility = View.VISIBLE
        // Still load stores but around default location
        map?.moveCamera(CameraUpdateFactory.newLatLngZoom(defaultLocation, 10f))
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
