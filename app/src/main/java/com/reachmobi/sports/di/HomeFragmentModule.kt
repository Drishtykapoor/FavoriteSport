package com.reachmobi.sports.di

import com.reachmobi.sports.view.HomeFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class HomeFragmentModule {

    @ContributesAndroidInjector(
        modules = [
            HomeFragmentDependenciesModule::class,
        ]
    )
    abstract fun provideHomeFragment(): HomeFragment

}