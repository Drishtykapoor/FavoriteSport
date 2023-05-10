package com.reachmobi.sports.di

import com.reachmobi.sports.view.AllPlayersFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class PlayerFragmentModule {

    @ContributesAndroidInjector(
        modules = [
            PlayerFragmentDependenciesModule::class
        ]
    )
    abstract fun provideHomeFragment(): AllPlayersFragment
}