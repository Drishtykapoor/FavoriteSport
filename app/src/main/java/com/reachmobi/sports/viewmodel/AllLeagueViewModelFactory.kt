package com.reachmobi.sports.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.reachmobi.sports.repository.LeagueRepository

class AllLeagueViewModelFactory(private val leagueRepository: LeagueRepository): ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return AllLeagueViewModelImpl(leagueRepository) as T
    }

}