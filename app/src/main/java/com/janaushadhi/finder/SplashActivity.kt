package com.janaushadhi.finder

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.View
import android.view.animation.AnimationUtils
import androidx.appcompat.app.AppCompatActivity
import com.janaushadhi.finder.databinding.ActivitySplashBinding

/**
 * Splash screen with animated branding.
 * Displays the app icon, name, and tagline with staggered animations
 * before transitioning to MainActivity.
 */
@SuppressLint("CustomSplashScreen")
class SplashActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySplashBinding
    private val splashDuration = 2500L

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySplashBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Make status bar transparent for immersive splash
        window.statusBarColor = android.graphics.Color.TRANSPARENT
        window.decorView.systemUiVisibility = (
            View.SYSTEM_UI_FLAG_LAYOUT_STABLE or View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
        )

        startAnimations()
        scheduleTransition()
    }

    private fun startAnimations() {
        val scaleUp = AnimationUtils.loadAnimation(this, R.anim.scale_up)
        val slideUpFadeIn = AnimationUtils.loadAnimation(this, R.anim.slide_up_fade_in)
        val fadeIn = AnimationUtils.loadAnimation(this, R.anim.fade_in)

        // Icon: scale up with bounce (immediate)
        binding.iconContainer.visibility = View.VISIBLE
        binding.iconContainer.startAnimation(scaleUp)

        // Title: slide up + fade in (300ms delay)
        Handler(Looper.getMainLooper()).postDelayed({
            binding.tvSplashTitle.visibility = View.VISIBLE
            binding.tvSplashTitle.startAnimation(slideUpFadeIn)
        }, 300)

        // Tagline: slide up + fade in (600ms delay)
        Handler(Looper.getMainLooper()).postDelayed({
            binding.tvSplashTagline.visibility = View.VISIBLE
            binding.tvSplashTagline.startAnimation(slideUpFadeIn)
        }, 600)

        // Progress bar: fade in (900ms delay)
        Handler(Looper.getMainLooper()).postDelayed({
            binding.progressBar.visibility = View.VISIBLE
            binding.progressBar.startAnimation(fadeIn)
        }, 900)

        // Initiative text: fade in (1100ms delay)
        Handler(Looper.getMainLooper()).postDelayed({
            binding.tvInitiative.visibility = View.VISIBLE
            binding.tvInitiative.startAnimation(fadeIn)
        }, 1100)
    }

    private fun scheduleTransition() {
        Handler(Looper.getMainLooper()).postDelayed({
            startActivity(Intent(this, MainActivity::class.java))
            overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out)
            finish()
        }, splashDuration)
    }
}
