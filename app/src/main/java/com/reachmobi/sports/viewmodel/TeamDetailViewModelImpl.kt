package com.reachmobi.sports.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.reachmobi.sports.repository.TeamsRepository
import com.reachmobi.sports.repository.viewstate.PlayerDetailViewState
import com.reachmobi.sports.repository.viewstate.TeamDetailViewState
import com.reachmobi.sports.repository.viewstate.TeamEventViewState
import kotlinx.coroutines.*
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.Date
import java.util.Timer
import kotlin.time.Duration
import kotlin.time.Duration.Companion.seconds
import kotlin.time.ExperimentalTime

@OptIn(ExperimentalTime::class)
class TeamDetailViewModelImpl(private val teamsRepository: TeamsRepository) : ViewModel(),
    TeamDetailViewModel {

    private val teamsData = MutableLiveData<TeamDetailViewState>()
    private val eventsData = MutableLiveData<TeamEventViewState>()

    override fun getData(teamId: String) {
        teamsData.value = TeamDetailViewState.Loading

        viewModelScope.launch {
            val response = teamsRepository.getTeamDetails(teamId)
            withContext(Dispatchers.Main) {
                if (response.isSuccessful) {
                    if (response.body() != null) {
                        val data = response.body()!!.teams
                        if (data.isEmpty()) {
                            teamsData.value = TeamDetailViewState.Empty
                        } else {
                            teamsData.value = TeamDetailViewState.Success(response.body()!!)
                        }
                    } else teamsData.value = TeamDetailViewState.Empty
                } else {
                    teamsData.value = TeamDetailViewState.Error(response.message())
                }
            }
        }
    }

    override fun getTeamEvents(teamId: String) {
        eventsData.value = TeamEventViewState.Loading

        viewModelScope.launch {
            val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")
            var current = LocalDateTime.now().format(formatter)
            Log.d("time", "startedAt $current")
            delay(3.seconds)
            current = LocalDateTime.now().format(formatter)
            Log.d("time", "startedAt $current")
            try {
                val response = teamsRepository.getTeamEvents(teamId)
                withContext(Dispatchers.Main) {
                    if (response.isSuccessful) {
                        if (response.body() != null) {
                            val data = response.body()!!.events
                            if (data.isEmpty()) {
                                eventsData.value = TeamEventViewState.Empty
                            } else {
                                eventsData.value = TeamEventViewState.Success(response.body()!!)
                            }
                        } else eventsData.value = TeamEventViewState.Empty
                    } else {
                        eventsData.value = TeamEventViewState.Error(response.message())
                    }
                }
            }catch (e: Exception){
                eventsData.value = TeamEventViewState.Error(e.message)
            }
        }
    }

    override fun getTeamsLiveData(): LiveData<TeamDetailViewState> = teamsData
    override fun getTeamEventsLiveData(): LiveData<TeamEventViewState> = eventsData
}