package com.reachmobi.sports.viewmodel

import androidx.lifecycle.LiveData
import com.reachmobi.sports.repository.viewstate.PlayerDetailViewState
import com.reachmobi.sports.repository.viewstate.TeamDetailViewState
import com.reachmobi.sports.repository.viewstate.TeamEventViewState

interface TeamDetailViewModel {
    fun getData(teamId: String)
    fun getTeamsLiveData(): LiveData<TeamDetailViewState>
    fun getTeamEventsLiveData(): LiveData<TeamEventViewState>
    fun getTeamEvents(teamId: String)
}
