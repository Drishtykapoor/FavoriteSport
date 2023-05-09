package com.reachmobi.sports.di

import androidx.lifecycle.ViewModelProvider
import com.reachmobi.sports.repository.LeagueRepository
import com.reachmobi.sports.repository.LeagueRepositoryImpl
import com.reachmobi.sports.repository.SportsService
import com.reachmobi.sports.view.AllLeagueFragment
import com.reachmobi.sports.viewmodel.AllLeagueViewModel
import com.reachmobi.sports.viewmodel.AllLeagueViewModelFactory
import com.reachmobi.sports.viewmodel.AllLeagueViewModelImpl
import dagger.Binds
import dagger.Module
import dagger.Provides

@Module(includes = [AllLeagueFragmentDependenciesModule.AllLeagueProvidesModule::class])
interface AllLeagueFragmentDependenciesModule {

    @Binds
    fun provideAllLeagueRepository(repositoryImpl: LeagueRepositoryImpl): LeagueRepository

    @Binds
    fun provideAllLeagueViewModel(viewModelImpl: AllLeagueViewModelImpl): AllLeagueViewModel

    @Module
    object AllLeagueProvidesModule {

        @Provides
        fun provideAllLeagueViewModelFactory(repository: LeagueRepository): AllLeagueViewModelFactory {
            return AllLeagueViewModelFactory(repository)
        }

        @Provides
        fun provideViewModel(
            fragment: AllLeagueFragment,
            viewModelFactory: AllLeagueViewModelFactory
        ): AllLeagueViewModelImpl {
            val v = ViewModelProvider(
                fragment,
                viewModelFactory
            )
            return v.get(AllLeagueViewModelImpl::class.java)
        }

        @Provides
        fun provideRepository(
            service: SportsService
        ): LeagueRepositoryImpl {
            return LeagueRepositoryImpl(service)
        }

    }

}

