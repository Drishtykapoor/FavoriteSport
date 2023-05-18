package com.reachmobi.sports.di

import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import com.reachmobi.sports.MainActivity
import com.reachmobi.sports.R
import dagger.Module
import dagger.Provides
import dagger.android.ContributesAndroidInjector

@Module
abstract class MainActivityModule {

    @ContributesAndroidInjector(
        modules = [
            UtilModule::class,
            NavigationModule::class,
            HomeFragmentModule::class,
            LoggerModule::class,
            DatabaseModule::class,
            PlayerFragmentModule::class,
            TeamDetailFragmentModule::class,
            PlayerDetailFragmentModule::class,
            SearchPlayersFragmentModule::class,
            MainActivityDependenciesModule::class,
            ApiModule::class
        ]
    )
    abstract fun contributeMainActivity(): MainActivity

    @Module
    object NavigationModule {
        @Provides
        fun provideNavigationController(mainActivity: MainActivity): NavController {
            val navHostFragment =
                mainActivity.supportFragmentManager
                    .findFragmentById(R.id.nav_host_fragment) as NavHostFragment
            return navHostFragment.navController
        }
    }
}