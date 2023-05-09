package com.reachmobi.sports.repository.viewstate

import com.reachmobi.sports.repository.pojo.AllPlayers

sealed class SearchPlayerViewState {
    object Empty : SearchPlayerViewState()
    object Loading : SearchPlayerViewState()
    data class Success(val allPlayers: AllPlayers) : SearchPlayerViewState()
    data class Error(val error: String?) : SearchPlayerViewState()
}