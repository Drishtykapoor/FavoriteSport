package com.reachmobi.sports.di

import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import com.reachmobi.sports.OnBoardingActivity
import com.reachmobi.sports.R
import dagger.Module
import dagger.Provides
import dagger.android.ContributesAndroidInjector

@Module
abstract class OnBoardingActivityModule {

    @ContributesAndroidInjector(
        modules = [
            UtilModule::class,
            NavigationModule::class,
            AllLeagueFragmentModule::class,
            AllTeamsFragmentModule::class,
            ApiModule::class
        ]
    )
    abstract fun contributeMainActivity(): OnBoardingActivity

    @Module
    object NavigationModule {
        @Provides
        fun provideNavigationController(activity: OnBoardingActivity): NavController {
            val navHostFragment =
                activity.supportFragmentManager
                    .findFragmentById(R.id.nav_host_fragment) as NavHostFragment
            return navHostFragment.navController
        }
    }
}