package com.reachmobi.sports.di

import androidx.lifecycle.ViewModelProvider
import com.reachmobi.sports.repository.PlayersRepository
import com.reachmobi.sports.repository.PlayersRepositoryImpl
import com.reachmobi.sports.repository.SportsService
import com.reachmobi.sports.view.SearchPlayersFragment
import com.reachmobi.sports.viewmodel.SearchPlayerViewModelFactory
import com.reachmobi.sports.viewmodel.SearchPlayerViewModelImpl
import com.reachmobi.sports.viewmodel.TeamDetailViewModel
import com.reachmobi.sports.viewmodel.TeamDetailViewModelImpl
import dagger.Binds
import dagger.Module
import dagger.Provides

@Module(includes = [SearchPlayerFragmentDependenciesModule.SearchFragmentProvidesModule::class])
interface SearchPlayerFragmentDependenciesModule {

    @Binds
    fun provideTeamDetailRepository(repositoryImpl: PlayersRepositoryImpl): PlayersRepository

    @Binds
    fun provideTeamDetailViewModel(teamDetailViewModelImpl: TeamDetailViewModelImpl): TeamDetailViewModel

    @Module
    object SearchFragmentProvidesModule {

        @Provides
        fun provideTeamDetailViewModelFactory(repository: PlayersRepository): SearchPlayerViewModelFactory {
            return SearchPlayerViewModelFactory(repository)
        }

        @Provides
        fun provideViewModel(
            fragment: SearchPlayersFragment,
            viewModelFactory: SearchPlayerViewModelFactory
        ): SearchPlayerViewModelImpl {
            val v = ViewModelProvider(
                fragment,
                viewModelFactory
            )
            return v.get(SearchPlayerViewModelImpl::class.java)
        }

        @Provides
        fun provideRepository(
            service: SportsService
        ): PlayersRepositoryImpl {
            return PlayersRepositoryImpl(service)
        }

    }

}

