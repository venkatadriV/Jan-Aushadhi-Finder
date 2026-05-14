package com.janaushadhi.finder.data.repository

import android.util.Log
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.GeoPoint
import com.janaushadhi.finder.data.model.Store
import kotlinx.coroutines.tasks.await
import kotlin.math.*

/**
 * Repository that fetches store data from Firebase Firestore.
 * Falls back to local hardcoded data if Firestore is unavailable.
 */
class FirestoreStoreRepository {

    private val firestore = FirebaseFirestore.getInstance()
    private val storesCollection = firestore.collection("stores")
    private val localRepository = StoreRepository()

    /**
     * Fetch stores from Firestore within a given radius.
     * Falls back to StoreRepository's local data on failure.
     */
    suspend fun getNearbyStores(
        userLat: Double,
        userLng: Double,
        radiusKm: Double = 50.0
    ): List<Store> {
        return try {
            val snapshot = storesCollection.get().await()
            val allStores = snapshot.documents.mapNotNull { doc ->
                try {
                    val geoPoint = doc.getGeoPoint("location") ?: return@mapNotNull null
                    val name = doc.getString("name") ?: return@mapNotNull null
                    val address = doc.getString("address") ?: ""
                    val phone = doc.getString("phone") ?: ""
                    val rating = doc.getDouble("rating")?.toFloat() ?: 4.0f
                    val isOpen = doc.getBoolean("isOpen") ?: true

                    val distanceKm = haversineDistance(
                        userLat, userLng,
                        geoPoint.latitude, geoPoint.longitude
                    )

                    Store(
                        id = doc.id.hashCode(),
                        name = name,
                        address = address,
                        latitude = geoPoint.latitude,
                        longitude = geoPoint.longitude,
                        phone = phone,
                        distanceKm = distanceKm,
                        rating = rating,
                        isOpen = isOpen
                    )
                } catch (e: Exception) {
                    Log.w(TAG, "Error parsing store document: ${e.message}")
                    null
                }
            }.sortedBy { it.distanceKm }

            val withinRadius = allStores.filter { it.distanceKm <= radiusKm }

            if (withinRadius.isNotEmpty()) {
                Log.d(TAG, "Fetched ${withinRadius.size} stores from Firestore within ${radiusKm}km")
                withinRadius
            } else if (allStores.isNotEmpty()) {
                // Show nearest 10 from Firestore even if outside radius
                Log.d(TAG, "No Firestore stores within ${radiusKm}km, showing nearest ${allStores.take(10).size}")
                allStores.take(10)
            } else {
                Log.d(TAG, "No Firestore stores found, using local data")
                localRepository.getNearbyStores(userLat, userLng, radiusKm)
            }
        } catch (e: Exception) {
            Log.w(TAG, "Firestore unavailable, using local data: ${e.message}")
            localRepository.getNearbyStores(userLat, userLng, radiusKm)
        }
    }

    /**
     * Add a store to Firestore (admin use / seed data).
     */
    suspend fun addStore(
        name: String,
        address: String,
        lat: Double,
        lng: Double,
        phone: String,
        rating: Float = 4.0f
    ) {
        val storeData = hashMapOf(
            "name" to name,
            "address" to address,
            "location" to GeoPoint(lat, lng),
            "phone" to phone,
            "rating" to rating,
            "isOpen" to true
        )
        storesCollection.add(storeData).await()
    }

    /**
     * Seed Firestore with sample store data (call once for testing).
     */
    suspend fun seedSampleStores() {
        val sampleStores = listOf(
            mapOf("name" to "Jan-Aushadhi Kendra - AIIMS", "address" to "AIIMS Campus, Ansari Nagar, New Delhi 110029", "location" to GeoPoint(28.5672, 77.2100), "phone" to "+91-11-26588500", "rating" to 4.5, "isOpen" to true),
            mapOf("name" to "Jan-Aushadhi Kendra - Safdarjung", "address" to "Safdarjung Hospital, New Delhi 110029", "location" to GeoPoint(28.5685, 77.2066), "phone" to "+91-11-26730000", "rating" to 4.3, "isOpen" to true),
            mapOf("name" to "Jan-Aushadhi Kendra - RML Hospital", "address" to "Ram Manohar Lohia Hospital, BKS Marg, New Delhi 110001", "location" to GeoPoint(28.6270, 77.2036), "phone" to "+91-11-23404444", "rating" to 4.2, "isOpen" to true),
            mapOf("name" to "PMBJP Store - Connaught Place", "address" to "CP Metro Station Complex, Block A, New Delhi 110001", "location" to GeoPoint(28.6315, 77.2167), "phone" to "+91-98765-43210", "rating" to 4.6, "isOpen" to true),
            mapOf("name" to "Jan-Aushadhi Kendra - GTB Hospital", "address" to "GTB Hospital, Dilshad Garden, Delhi 110095", "location" to GeoPoint(28.6862, 77.3150), "phone" to "+91-11-22586262", "rating" to 4.1, "isOpen" to true),
            mapOf("name" to "Jan-Aushadhi Kendra - KEM Hospital", "address" to "KEM Hospital, Parel, Mumbai 400012", "location" to GeoPoint(19.0003, 72.8410), "phone" to "+91-22-24107000", "rating" to 4.4, "isOpen" to true),
            mapOf("name" to "PMBJP Store - Dadar", "address" to "Dadar Railway Station Complex, Mumbai 400014", "location" to GeoPoint(19.0178, 72.8478), "phone" to "+91-98765-12345", "rating" to 4.3, "isOpen" to true),
            mapOf("name" to "Jan-Aushadhi Kendra - Victoria Hospital", "address" to "Victoria Hospital Campus, Fort, Bangalore 560002", "location" to GeoPoint(12.9580, 77.5730), "phone" to "+91-80-26701150", "rating" to 4.2, "isOpen" to true),
            mapOf("name" to "Jan-Aushadhi Kendra - NIMHANS", "address" to "NIMHANS, Hosur Road, Bangalore 560029", "location" to GeoPoint(12.9417, 77.5960), "phone" to "+91-80-26995000", "rating" to 4.5, "isOpen" to true),
            mapOf("name" to "PMBJP Store - MG Road", "address" to "MG Road Metro Station, Bangalore 560001", "location" to GeoPoint(12.9756, 77.6066), "phone" to "+91-98765-67890", "rating" to 4.4, "isOpen" to true)
        )
        
        for (store in sampleStores) {
            try {
                storesCollection.add(store).await()
            } catch (e: Exception) {
                Log.e(TAG, "Error seeding store: ${e.message}")
            }
        }
        Log.d(TAG, "Seeded ${sampleStores.size} sample stores to Firestore")
    }

    /**
     * Haversine formula to calculate distance between two coordinates.
     */
    private fun haversineDistance(
        lat1: Double, lon1: Double,
        lat2: Double, lon2: Double
    ): Double {
        val r = 6371.0 // Earth radius in km
        val dLat = Math.toRadians(lat2 - lat1)
        val dLon = Math.toRadians(lon2 - lon1)
        val a = sin(dLat / 2).pow(2) +
                cos(Math.toRadians(lat1)) * cos(Math.toRadians(lat2)) *
                sin(dLon / 2).pow(2)
        val c = 2 * asin(sqrt(a))
        return (r * c * 10).roundToInt() / 10.0
    }

    companion object {
        private const val TAG = "FirestoreStoreRepo"
    }
}
