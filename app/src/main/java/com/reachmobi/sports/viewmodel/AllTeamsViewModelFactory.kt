package com.reachmobi.sports.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.reachmobi.sports.repository.TeamsRepository

class AllTeamsViewModelFactory (private val teamsRepository: TeamsRepository): ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return AllTeamsViewModelImpl(teamsRepository) as T
    }

}