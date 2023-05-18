package com.reachmobi.sports.di

import androidx.lifecycle.ViewModelProvider
import com.reachmobi.sports.repository.PlayersRepository
import com.reachmobi.sports.repository.PlayersRepositoryImpl
import com.reachmobi.sports.repository.SportsService
import com.reachmobi.sports.repository.dao.FavSportsDatabase
import com.reachmobi.sports.repository.dao.PlayerDao
import com.reachmobi.sports.view.PlayerDetailFragment
import com.reachmobi.sports.viewmodel.*
import dagger.Binds
import dagger.Module
import dagger.Provides

@Module(includes = [PlayerDetailFragmentDependenciesModule.PlayerDetailFragmentProvidesModule::class])
interface PlayerDetailFragmentDependenciesModule {

    @Binds
    fun bindRepository(repositoryImpl: PlayersRepositoryImpl): PlayersRepository

    @Binds
    fun bindViewModel(viewModelImpl: PlayerDetailViewModelImpl): PlayerDetailViewModel

    @Module
    object PlayerDetailFragmentProvidesModule {

        @Provides
        fun provideViewModelFactory(repository: PlayersRepository): PlayerDetailViewModelFactory {
            return PlayerDetailViewModelFactory(repository)
        }

        @Provides
        fun provideViewModel(
            fragment: PlayerDetailFragment,
            viewModelFactory: PlayerDetailViewModelFactory
        ): PlayerDetailViewModelImpl {
            val v = ViewModelProvider(
                fragment,
                viewModelFactory
            )
            return v.get(PlayerDetailViewModelImpl::class.java)
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

