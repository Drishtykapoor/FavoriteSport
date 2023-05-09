package com.reachmobi.sports.di

import com.reachmobi.sports.view.HomeFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class PlayerDetailFragmentModule {

    @ContributesAndroidInjector(
        modules = [
        ]
    )
    abstract fun provideHomeFragment(): HomeFragment
}