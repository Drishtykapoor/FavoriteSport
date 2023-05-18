package com.reachmobi.sports.di

import androidx.lifecycle.ViewModelProvider
import com.reachmobi.sports.repository.PlayersRepository
import com.reachmobi.sports.repository.PlayersRepositoryImpl
import com.reachmobi.sports.repository.SportsService
import com.reachmobi.sports.repository.dao.FavSportsDatabase
import com.reachmobi.sports.repository.dao.PlayerDao
import com.reachmobi.sports.view.SearchPlayersFragment
import com.reachmobi.sports.viewmodel.*
import dagger.Binds
import dagger.Module
import dagger.Provides

@Module(includes = [SearchPlayerFragmentDependenciesModule.SearchFragmentProvidesModule::class])
interface SearchPlayerFragmentDependenciesModule {

    @Binds
    fun bindRepository(repositoryImpl: PlayersRepositoryImpl): PlayersRepository

    @Binds
    fun bindViewModel(viewModelImpl: SearchPlayerViewModelImpl): SearchPlayerViewModel

    @Module
    object SearchFragmentProvidesModule {

        @Provides
        fun provideViewModelFactory(repository: PlayersRepository): SearchPlayerViewModelFactory {
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
            service: SportsService,
            database: FavSportsDatabase
        ): PlayersRepositoryImpl {
            return PlayersRepositoryImpl(service, database.playerDao())
        }

    }

}

