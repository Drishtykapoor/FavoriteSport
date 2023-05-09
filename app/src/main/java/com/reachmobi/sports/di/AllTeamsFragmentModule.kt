package com.reachmobi.sports.di

import com.reachmobi.sports.view.AllTeamsFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class AllTeamsFragmentModule {

    @ContributesAndroidInjector(
        modules = [
            AllTeamsFragmentDependenciesModule::class,
        ]
    )
    abstract fun provideHomeFragment(): AllTeamsFragment
}