package com.reachmobi.sports.viewmodel

import androidx.lifecycle.LiveData
import com.reachmobi.sports.repository.viewstate.PlayersViewState

interface PlayersViewModel {
    fun getData(teamID: String)
    fun getAllTeamPlayers(): LiveData<PlayersViewState>
}