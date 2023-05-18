package com.reachmobi.sports.di

import androidx.lifecycle.ViewModelProvider
import com.reachmobi.sports.repository.TeamsRepository
import com.reachmobi.sports.repository.TeamsRepositoryImpl
import com.reachmobi.sports.repository.TeamsService
import com.reachmobi.sports.view.TeamDetailExperimentalImageView
import com.reachmobi.sports.view.TeamDetailExperimentalTextView
import com.reachmobi.sports.view.TeamDetailFragment
import com.reachmobi.sports.view.ViewPlugin
import com.reachmobi.sports.viewmodel.TeamDetailViewModel
import com.reachmobi.sports.viewmodel.TeamDetailViewModelFactory
import com.reachmobi.sports.viewmodel.TeamDetailViewModelImpl
import dagger.Binds
import dagger.Module
import dagger.Provides

@Module(includes = [TeamDetailFragmentDependenciesModule.TeamDetailProvidesModule::class])
interface TeamDetailFragmentDependenciesModule {

//    @Binds
//    fun provideViewPlugin(teamDetailExperimentalImageView: TeamDetailExperimentalImageView): ViewPlugin

    @Binds
    fun provideTeamDetailRepository(teamsRepositoryImpl: TeamsRepositoryImpl): TeamsRepository

    @Binds
    fun provideTeamDetailViewModel(teamDetailViewModelImpl: TeamDetailViewModelImpl): TeamDetailViewModel

    @Module
    object TeamDetailProvidesModule {

        @Provides
        fun providePlugins(
            plugin: TeamDetailExperimentalTextView,
            pluginImage: TeamDetailExperimentalImageView
        ): Array<ViewPlugin> =
            arrayOf(plugin, pluginImage)

        @Provides
        fun provideTeamDetailViewModelFactory(teamsRepository: TeamsRepository): TeamDetailViewModelFactory {
            return TeamDetailViewModelFactory(teamsRepository)
        }

        @Provides
        fun provideViewModel(
            teamDetailFragment: TeamDetailFragment,
            teamDetailViewModelFactory: TeamDetailViewModelFactory
        ): TeamDetailViewModelImpl {
            val v = ViewModelProvider(
                teamDetailFragment, teamDetailViewModelFactory
            )
            return v.get(TeamDetailViewModelImpl::class.java)
        }

        @Provides
        fun provideRepository(
            teamsService: TeamsService
        ): TeamsRepositoryImpl {
            return TeamsRepositoryImpl(teamsService)
        }

    }

}

