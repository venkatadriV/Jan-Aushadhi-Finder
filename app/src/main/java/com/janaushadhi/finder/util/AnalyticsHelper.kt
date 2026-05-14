package com.janaushadhi.finder.util

import android.os.Bundle
import com.google.firebase.analytics.FirebaseAnalytics

/**
 * Helper object to log custom analytics events to Firebase Analytics.
 * Tracks user interactions for understanding usage patterns.
 */
object AnalyticsHelper {

    private var analytics: FirebaseAnalytics? = null

    fun init(firebaseAnalytics: FirebaseAnalytics) {
        analytics = firebaseAnalytics
    }

    /** Log when user searches for a medicine */
    fun logSearch(query: String) {
        analytics?.logEvent(FirebaseAnalytics.Event.SEARCH, Bundle().apply {
            putString(FirebaseAnalytics.Param.SEARCH_TERM, query)
        })
    }

    /** Log when user views a medicine detail */
    fun logViewMedicine(medicineId: Int, medicineName: String) {
        analytics?.logEvent(FirebaseAnalytics.Event.VIEW_ITEM, Bundle().apply {
            putInt("medicine_id", medicineId)
            putString("medicine_name", medicineName)
        })
    }

    /** Log when user adds a medicine to favorites */
    fun logAddFavorite(medicineName: String) {
        analytics?.logEvent("add_favorite", Bundle().apply {
            putString("medicine_name", medicineName)
        })
    }

    /** Log when user uses the savings calculator */
    fun logSavingsCalculation(totalSaved: Double) {
        analytics?.logEvent("calculate_savings", Bundle().apply {
            putDouble("total_saved", totalSaved)
        })
    }

    /** Log when user views a store */
    fun logViewStore(storeName: String, distanceKm: Double) {
        analytics?.logEvent("view_store", Bundle().apply {
            putString("store_name", storeName)
            putDouble("distance_km", distanceKm)
        })
    }

    /** Log when user sets a reminder */
    fun logSetReminder(medicineName: String) {
        analytics?.logEvent("set_reminder", Bundle().apply {
            putString("medicine_name", medicineName)
        })
    }

    /** Log when user shares a medicine */
    fun logShareMedicine(medicineName: String) {
        analytics?.logEvent(FirebaseAnalytics.Event.SHARE, Bundle().apply {
            putString("medicine_name", medicineName)
        })
    }

    /** Log when user requests directions to a store */
    fun logGetDirections(storeName: String) {
        analytics?.logEvent("get_directions", Bundle().apply {
            putString("store_name", storeName)
        })
    }

    /** Log screen views */
    fun logScreenView(screenName: String) {
        analytics?.logEvent(FirebaseAnalytics.Event.SCREEN_VIEW, Bundle().apply {
            putString(FirebaseAnalytics.Param.SCREEN_NAME, screenName)
        })
    }
}
