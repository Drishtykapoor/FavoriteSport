package com.reachmobi.sports.repository.viewstate

import com.reachmobi.sports.repository.pojo.AllTeamsResponse

sealed class AllTeamsViewState {
    object Empty : AllTeamsViewState()
    object Loading : AllTeamsViewState()
    data class Success(val allTeamsResponse: AllTeamsResponse) : AllTeamsViewState()
    data class Error(val error: String?) : AllTeamsViewState()
}