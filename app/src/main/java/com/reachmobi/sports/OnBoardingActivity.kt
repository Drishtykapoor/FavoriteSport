package com.reachmobi.sports

import android.os.Bundle
import android.os.PersistableBundle
import dagger.android.support.DaggerAppCompatActivity

class OnBoardingActivity : DaggerAppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.on_boarding_activity)
        setSupportActionBar(findViewById(R.id.toolbar))
        supportActionBar?.title = resources?.getText(R.string.welcome_to_onboarding)
    }

}