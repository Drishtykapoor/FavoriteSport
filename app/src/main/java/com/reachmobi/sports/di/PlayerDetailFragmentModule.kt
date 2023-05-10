package com.reachmobi.sports.di

import com.reachmobi.sports.view.HomeFragment
import com.reachmobi.sports.view.PlayerDetailFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class PlayerDetailFragmentModule {

    @ContributesAndroidInjector(
        modules = [
            PlayerDetailFragmentDependenciesModule::class
        ]
    )
    abstract fun providePlayerDetailFragment(): PlayerDetailFragment
}