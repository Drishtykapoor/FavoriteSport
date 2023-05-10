package com.reachmobi.sports.di

import androidx.lifecycle.ViewModelProvider
import com.reachmobi.sports.repository.PlayersRepository
import com.reachmobi.sports.repository.PlayersRepositoryImpl
import com.reachmobi.sports.repository.SportsService
import com.reachmobi.sports.view.AllPlayersFragment
import com.reachmobi.sports.viewmodel.PlayersViewModel
import com.reachmobi.sports.viewmodel.PlayersViewModelFactory
import com.reachmobi.sports.viewmodel.PlayersViewModelImpl
import dagger.Binds
import dagger.Module
import dagger.Provides

@Module(includes = [PlayerFragmentDependenciesModule.PlayerFragmentProvidesModule::class])
interface PlayerFragmentDependenciesModule {

    @Binds
    fun provideAllLeagueRepository(repositoryImpl: PlayersRepositoryImpl): PlayersRepository

    @Binds
    fun provideAllLeagueViewModel(viewModelImpl: PlayersViewModelImpl): PlayersViewModel

    @Module
    object PlayerFragmentProvidesModule {

        @Provides
        fun provideAllLeagueViewModelFactory(repository: PlayersRepository): PlayersViewModelFactory {
            return PlayersViewModelFactory(repository)
        }

        @Provides
        fun provideViewModel(
            fragment: AllPlayersFragment,
            viewModelFactory: PlayersViewModelFactory
        ): PlayersViewModelImpl {
            val v = ViewModelProvider(
                fragment,
                viewModelFactory
            )
            return v.get(PlayersViewModelImpl::class.java)
        }

        @Provides
        fun provideRepository(
            service: SportsService
        ): PlayersRepositoryImpl {
            return PlayersRepositoryImpl(service)
        }

    }

}

