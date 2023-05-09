package com.reachmobi.sports

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.preference.PreferenceManager
import com.google.android.gms.ads.MobileAds
import dagger.android.support.DaggerAppCompatActivity

//import com.google.firebase.analytics.FirebaseAnalytics
//import com.google.firebase.analytics.ktx.analytics
//import com.google.firebase.analytics.ktx.logEvent
//import com.google.firebase.ktx.Firebase

class MainActivity : DaggerAppCompatActivity() {

//    private lateinit var firebaseAnalytics: FirebaseAnalytics


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this)
        val teamId = sharedPreferences?.getString("fav_team_id", null)
        if (teamId.isNullOrEmpty()) {
            startActivity(Intent(this, OnBoardingActivity::class.java))
            finish()
        }
        setContentView(R.layout.main_activity)
        // To make our toolbar show the application
        setSupportActionBar(findViewById(R.id.toolbar))
        MobileAds.initialize(this) {}
//        firebaseAnalytics = Firebase.analytics
//        firebaseAnalytics.logEvent(FirebaseAnalytics.Event.SELECT_ITEM) {
//                param(FirebaseAnalytics.Param.ITEM_ID, "1")
//                param(FirebaseAnalytics.Param.ITEM_NAME, "Select Team Button")
//                param(FirebaseAnalytics.Param.ITEM_LIST_NAME, "image")
//        }
    }
}