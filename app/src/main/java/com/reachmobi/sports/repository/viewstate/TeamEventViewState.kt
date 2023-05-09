package com.reachmobi.sports.repository.viewstate

import com.reachmobi.sports.repository.pojo.UpcomingEvents

sealed class TeamEventViewState {
    object Empty : TeamEventViewState()
    object Loading : TeamEventViewState()
    data class Success(val upcomingEvents: UpcomingEvents) : TeamEventViewState()
    data class Error(val error: String?) : TeamEventViewState()
}