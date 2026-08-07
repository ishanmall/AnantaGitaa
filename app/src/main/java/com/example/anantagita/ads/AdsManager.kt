package com.anantagita.ads

import android.app.Activity
import android.content.Context
import com.google.android.gms.ads.AdError
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.FullScreenContentCallback
import com.google.android.gms.ads.LoadAdError
import com.google.android.gms.ads.MobileAds
import com.google.android.gms.ads.interstitial.InterstitialAd
import com.google.android.gms.ads.interstitial.InterstitialAdLoadCallback

object AdsManager {

    private var interstitialAd: InterstitialAd? = null
    private var isInterstitialLoading = false

    fun initialize(context: Context) {
        MobileAds.initialize(context) {}
    }

    fun loadInterstitial(context: Context, adUnitId: String) {
        if (isInterstitialLoading || interstitialAd != null) return
        isInterstitialLoading = true

        val adRequest = AdRequest.Builder().build()
        InterstitialAd.load(
            context,
            adUnitId,
            adRequest,
            object : InterstitialAdLoadCallback() {
                override fun onAdLoaded(ad: InterstitialAd) {
                    interstitialAd = ad
                    isInterstitialLoading = false
                }

                override fun onAdFailedToLoad(error: LoadAdError) {
                    interstitialAd = null
                    isInterstitialLoading = false
                }
            }
        )
    }

    fun showInterstitial(
        activity: Activity,
        adUnitId: String,
        onDismiss: (() -> Unit)? = null
    ) {
        interstitialAd?.let { ad ->
            ad.fullScreenContentCallback = object : FullScreenContentCallback() {
                override fun onAdDismissedFullScreenContent() {
                    interstitialAd = null
                    onDismiss?.invoke()
                    loadInterstitial(activity, adUnitId)
                }

                override fun onAdFailedToShowFullScreenContent(error: AdError) {
                    interstitialAd = null
                    onDismiss?.invoke()
                    loadInterstitial(activity, adUnitId)
                }
            }
            ad.show(activity)
        } ?: run {
            onDismiss?.invoke()
            loadInterstitial(activity, adUnitId)
        }
    }
}