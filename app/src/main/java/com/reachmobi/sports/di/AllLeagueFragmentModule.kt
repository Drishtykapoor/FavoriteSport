package com.reachmobi.sports.di

import com.reachmobi.sports.view.AllLeagueFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class AllLeagueFragmentModule {

    @ContributesAndroidInjector(
        modules = [
            AllLeagueFragmentDependenciesModule::class,
        ]
    )
    abstract fun provideAllLeagueFragment(): AllLeagueFragment
}