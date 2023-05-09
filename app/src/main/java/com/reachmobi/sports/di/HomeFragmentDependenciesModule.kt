package com.reachmobi.sports.di

import com.reachmobi.sports.adapter.HomeViewPagerAdapter
import com.reachmobi.sports.view.HomeFragment
import dagger.Module
import dagger.Provides

@Module
object HomeFragmentDependenciesModule {

    @Provides
    fun provideHomeViewModelFactory(fragment: HomeFragment): HomeViewPagerAdapter {
        return HomeViewPagerAdapter(fragment.requireActivity())
    }
}

