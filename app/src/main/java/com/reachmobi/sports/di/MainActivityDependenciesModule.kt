package com.reachmobi.sports.di

import androidx.lifecycle.ViewModelProvider
import com.reachmobi.sports.MainActivity
import com.reachmobi.sports.repository.MainActivityRepository
import com.reachmobi.sports.repository.MainActivityRepositoryImpl
import com.reachmobi.sports.viewmodel.MainActivityViewModel
import com.reachmobi.sports.viewmodel.MainActivityViewModelFactory
import com.reachmobi.sports.viewmodel.MainActivityViewModelImpl
import dagger.Binds
import dagger.Module
import dagger.Provides

@Module(includes = [MainActivityDependenciesModule.MainActivityProvidesModule::class])
interface MainActivityDependenciesModule {

    @Binds
    fun provideMainActivityRepository(mainActivityRepositoryImpl: MainActivityRepositoryImpl): MainActivityRepository

    @Binds
    fun provideMainActivityViewModel(mainActivityViewModelImpl: MainActivityViewModelImpl): MainActivityViewModel

    @Module
    object MainActivityProvidesModule {

        @Provides
        fun provideMainActivityViewModelFactory(mainActivityRepository: MainActivityRepository): MainActivityViewModelFactory {
            return MainActivityViewModelFactory(mainActivityRepository)
        }

        @Provides
        fun provideViewModel(
            mainActivity: MainActivity,
            mainActivityViewModelFactory: MainActivityViewModelFactory
        ): MainActivityViewModelImpl {
            val v = ViewModelProvider(
                mainActivity, mainActivityViewModelFactory
            )
            return v[MainActivityViewModelImpl::class.java]
        }


    }

}

