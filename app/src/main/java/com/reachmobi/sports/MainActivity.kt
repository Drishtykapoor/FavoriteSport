package com.reachmobi.sports

import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import com.google.android.gms.ads.MobileAds
import com.google.firebase.FirebaseApp
import com.google.firebase.ktx.Firebase
import com.reachmobi.sports.viewmodel.MainActivityViewModel
import dagger.android.support.DaggerAppCompatActivity
import javax.inject.Inject

class MainActivity : DaggerAppCompatActivity() {

    @Inject
    lateinit var experimentalWidgetViewModel: MainActivityViewModel

    @Inject
    lateinit var sharedPreferences: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        FirebaseApp.initializeApp(this)
        val teamId = sharedPreferences.getString("fav_team_id", null)
        if (teamId.isNullOrEmpty()) {
            startActivity(Intent(this, OnBoardingActivity::class.java))
            finish()
        }
        setContentView(R.layout.main_activity)
        setSupportActionBar(findViewById(R.id.toolbar))
        MobileAds.initialize(this) {}
    }

    override fun onResume() {
        super.onResume()

        experimentalWidgetViewModel.getData()
        experimentalWidgetViewModel.getWidgetLiveData().observe(this) {
            it.forEach {
                entry -> sharedPreferences.edit()?.putString(entry.key, entry.value)?.apply()
            }
        }
    }
}