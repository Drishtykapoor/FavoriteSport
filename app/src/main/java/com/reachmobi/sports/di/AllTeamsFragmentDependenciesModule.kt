package com.reachmobi.sports.di

import androidx.lifecycle.ViewModelProvider
import com.reachmobi.sports.repository.TeamsRepository
import com.reachmobi.sports.repository.TeamsRepositoryImpl
import com.reachmobi.sports.repository.TeamsService
import com.reachmobi.sports.view.AllTeamsFragment
import com.reachmobi.sports.viewmodel.AllTeamsViewModel
import com.reachmobi.sports.viewmodel.AllTeamsViewModelFactory
import com.reachmobi.sports.viewmodel.AllTeamsViewModelImpl
import dagger.Binds
import dagger.Module
import dagger.Provides

@Module(includes = [AllTeamsFragmentDependenciesModule.AllTeamsProvidesModule::class])
interface AllTeamsFragmentDependenciesModule {

    @Binds
    fun provideTeamDetailRepository(repositoryImpl: TeamsRepositoryImpl): TeamsRepository

    @Binds
    fun provideTeamDetailViewModel(viewModelImpl: AllTeamsViewModelImpl): AllTeamsViewModel

    @Module
    object AllTeamsProvidesModule {

        @Provides
        fun provideTeamDetailViewModelFactory(repository: TeamsRepository): AllTeamsViewModelFactory {
            return AllTeamsViewModelFactory(repository)
        }

        @Provides
        fun provideViewModel(
            fragment: AllTeamsFragment,
            viewModelFactory: AllTeamsViewModelFactory
        ): AllTeamsViewModelImpl {
            val v = ViewModelProvider(
                fragment,
                viewModelFactory
            )
            return v.get(AllTeamsViewModelImpl::class.java)
        }

        @Provides
        fun provideRepository(
            service: TeamsService
        ): TeamsRepositoryImpl {
            return TeamsRepositoryImpl(service)
        }

    }

}

