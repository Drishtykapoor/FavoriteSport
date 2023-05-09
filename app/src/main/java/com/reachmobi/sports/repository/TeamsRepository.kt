package com.reachmobi.sports.repository

import com.reachmobi.sports.repository.pojo.AllTeamsResponse
import com.reachmobi.sports.repository.pojo.TeamDetailResponse
import com.reachmobi.sports.repository.pojo.UpcomingEvents
import retrofit2.Response

interface TeamsRepository {
    suspend fun getAllTeamsForLeague(leagueId : String): Response<AllTeamsResponse>
    suspend fun getTeamDetails(teamId: String): Response<TeamDetailResponse>
    suspend fun getTeamEvents(teamId: String): Response<UpcomingEvents>
}
