package com.reachmobi.sports

import android.content.Intent
import android.os.Bundle
import androidx.preference.PreferenceManager
import com.google.android.gms.ads.MobileAds
import dagger.android.support.DaggerAppCompatActivity

class MainActivity : DaggerAppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this)
        val teamId = sharedPreferences?.getString("fav_team_id", null)
        if (teamId.isNullOrEmpty()) {
            startActivity(Intent(this, OnBoardingActivity::class.java))
            finish()
        }
        setContentView(R.layout.main_activity)
        setSupportActionBar(findViewById(R.id.toolbar))
        MobileAds.initialize(this) {}
    }
}