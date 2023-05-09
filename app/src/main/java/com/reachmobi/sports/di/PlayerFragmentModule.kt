package com.reachmobi.sports.di

import com.reachmobi.sports.view.PlayersFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class PlayerFragmentModule {

    @ContributesAndroidInjector(
        modules = [
        ]
    )
    abstract fun provideHomeFragment(): PlayersFragment
}