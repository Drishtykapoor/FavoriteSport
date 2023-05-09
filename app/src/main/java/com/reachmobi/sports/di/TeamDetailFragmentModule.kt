package com.reachmobi.sports.di

import com.reachmobi.sports.view.TeamDetailFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class TeamDetailFragmentModule {

    @ContributesAndroidInjector(
        modules = [
            TeamDetailFragmentDependenciesModule::class
        ]
    )
    abstract fun provideTeamDetailFragment(): TeamDetailFragment

}