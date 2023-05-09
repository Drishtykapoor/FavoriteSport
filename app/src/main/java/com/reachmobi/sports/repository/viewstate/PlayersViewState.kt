package com.reachmobi.sports.repository.viewstate

import com.reachmobi.sports.repository.pojo.AllPlayers

sealed class PlayersViewState {
    object Empty : PlayersViewState()
    object Loading : PlayersViewState()
    data class Success(val allPlayers: AllPlayers) : PlayersViewState()
    data class Error(val error: String?) : PlayersViewState()
}