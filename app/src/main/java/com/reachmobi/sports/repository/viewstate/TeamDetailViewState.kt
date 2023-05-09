package com.reachmobi.sports.repository.viewstate

import com.reachmobi.sports.repository.pojo.TeamDetailResponse

sealed class TeamDetailViewState {
    object Empty : TeamDetailViewState()
    object Loading : TeamDetailViewState()
    data class Success(val allTeamsResponse: TeamDetailResponse) : TeamDetailViewState()
    data class Error(val error: String?) : TeamDetailViewState()
}