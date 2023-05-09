package com.reachmobi.sports.repository

import com.reachmobi.sports.repository.pojo.AllLeagues

sealed class AllLeagueViewState {
    object Empty : AllLeagueViewState()
    object Loading : AllLeagueViewState()
    data class Success(val allLeagues: AllLeagues) : AllLeagueViewState()
    data class Error(val error: String?) : AllLeagueViewState()
}