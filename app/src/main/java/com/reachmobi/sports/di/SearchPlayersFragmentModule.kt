package com.reachmobi.sports.di

import com.reachmobi.sports.view.SearchPlayersFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class SearchPlayersFragmentModule {

    @ContributesAndroidInjector(
        modules = [
            SearchPlayerFragmentDependenciesModule::class
        ]
    )
    abstract fun provideSearchFragment(): SearchPlayersFragment
}