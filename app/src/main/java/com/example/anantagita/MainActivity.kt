package com.anantagita

import android.Manifest
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.core.content.ContextCompat
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.work.ExistingPeriodicWorkPolicy
import androidx.work.PeriodicWorkRequestBuilder
import androidx.work.WorkManager
import com.anantagita.ads.AdsManager
import com.anantagita.navigation.NavGraph
import com.anantagita.navigation.Screen
import com.anantagita.notifications.DailyWorker
import com.anantagita.ui.theme.AnantaGitaTheme
import java.util.concurrent.TimeUnit

class MainActivity : ComponentActivity() {

    // 🔹 Permission request launcher
    private val requestPermissionLauncher =
        registerForActivityResult(ActivityResultContracts.RequestPermission()) { isGranted ->
            if (isGranted) scheduleDailyShlokaNotification()
        }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d("MainActivity", "onCreate called")

        // ✅ Notification permission (Android 13+)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            if (ContextCompat.checkSelfPermission(
                    this,
                    Manifest.permission.POST_NOTIFICATIONS
                ) != PackageManager.PERMISSION_GRANTED
            ) {
                requestPermissionLauncher.launch(Manifest.permission.POST_NOTIFICATIONS)
            } else {
                scheduleDailyShlokaNotification()
            }
        } else {
            scheduleDailyShlokaNotification()
        }

        // ✅ Initialize Ads
        AdsManager.initialize(this)
        AdsManager.loadInterstitial(this, getString(R.string.interstitial_ad_unit_id))
        Log.d("MainActivity", "Ads initialized and interstitial preloaded")

        // ✅ Compose UI
        setContent {
            AnantaGitaTheme {
                val navController = rememberNavController()
                val backStackEntry by navController.currentBackStackEntryAsState()
                val currentRoute by remember { derivedStateOf { backStackEntry?.destination?.route } }

                Box(modifier = Modifier.fillMaxSize()) {
                    // 🔹 Navigation Graph
                    NavGraph(navController = navController)
                }

                // 🚀 Interstitial Ads → specific routes
                if (
                    currentRoute in listOf(
                        Screen.Home.route,
                        Screen.Feedback.route,
                        Screen.About.route,
                        Screen.PrivacyTerms.route
                    )
                ) {
                    LaunchedEffect(currentRoute) {
                        Log.d("MainActivity", "Showing interstitial on $currentRoute")
                        AdsManager.showInterstitial(
                            this@MainActivity,
                            getString(R.string.interstitial_ad_unit_id)
                        )
                    }
                }
            }
        }
    }

    // 🔹 Lifecycle logging
    override fun onStart() {
        super.onStart()
        Log.d("MainActivity", "onStart called")
    }

    override fun onResume() {
        super.onResume()
        Log.d("MainActivity", "onResume called")
    }

    override fun onPause() {
        super.onPause()
        Log.d("MainActivity", "onPause called")
    }

    override fun onStop() {
        super.onStop()
        Log.d("MainActivity", "onStop called")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d("MainActivity", "onDestroy called")
    }

    // 🔹 Daily notification worker
    private fun scheduleDailyShlokaNotification() {
        val workRequest = PeriodicWorkRequestBuilder<DailyWorker>(1, TimeUnit.DAYS).build()
        WorkManager.getInstance(this).enqueueUniquePeriodicWork(
            "DailyShlokaWork",
            ExistingPeriodicWorkPolicy.UPDATE,
            workRequest
        )
        Log.d("MainActivity", "Daily Shloka Worker scheduled")
    }
}