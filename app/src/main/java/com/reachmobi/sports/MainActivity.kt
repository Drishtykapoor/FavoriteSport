package com.reachmobi.sports

import android.content.Intent
import android.os.Bundle
import androidx.preference.PreferenceManager
import com.google.android.gms.ads.MobileAds
import com.reachmobi.sports.viewmodel.MainActivityViewModel
import dagger.android.support.DaggerAppCompatActivity
import javax.inject.Inject

class MainActivity : DaggerAppCompatActivity() {

    @Inject
    lateinit var experimentalWidgetViewModel: MainActivityViewModel

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

    override fun onResume() {
        super.onResume()

        experimentalWidgetViewModel.getData()
        experimentalWidgetViewModel.getWidgetLiveData().observe(this){
            val sharedPreferences =
                this.let { PreferenceManager.getDefaultSharedPreferences(it) }
            it.forEach{
                sharedPreferences?.edit()?.putString(it.key, it.value)?.apply()
            }
        }
    }
}