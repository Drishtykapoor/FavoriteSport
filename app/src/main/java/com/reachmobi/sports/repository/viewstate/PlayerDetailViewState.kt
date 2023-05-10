package com.reachmobi.sports.repository.viewstate

import com.reachmobi.sports.repository.pojo.AllPlayers

sealed class PlayerDetailViewState {
    object Empty : PlayerDetailViewState()
    object Loading : PlayerDetailViewState()
    data class Success(val allTeamsResponse: AllPlayers) : PlayerDetailViewState()
    data class Error(val error: String?) : PlayerDetailViewState()
}