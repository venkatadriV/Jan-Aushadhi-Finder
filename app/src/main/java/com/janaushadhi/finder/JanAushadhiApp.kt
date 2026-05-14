package com.janaushadhi.finder

import android.app.Application
import android.util.Log
import com.google.firebase.FirebaseApp
import com.google.firebase.analytics.FirebaseAnalytics
import com.google.firebase.crashlytics.FirebaseCrashlytics
import com.janaushadhi.finder.util.NotificationHelper

class JanAushadhiApp : Application() {
    
    lateinit var firebaseAnalytics: FirebaseAnalytics
        private set

    override fun onCreate() {
        super.onCreate()

        // Initialize Notification Channels
        NotificationHelper.createNotificationChannel(this)

        // Initialize Firebase
        try {
            FirebaseApp.initializeApp(this)
            firebaseAnalytics = FirebaseAnalytics.getInstance(this)
            
            // Enable Crashlytics collection
            FirebaseCrashlytics.getInstance().setCrashlyticsCollectionEnabled(true)
            
            Log.d(TAG, "Firebase initialized successfully")
        } catch (e: Exception) {
            Log.e(TAG, "Firebase initialization failed: ${e.message}")
        }
    }

    companion object {
        private const val TAG = "JanAushadhiApp"
    }
}
