package com.reachmobi.sports.repository

import com.reachmobi.sports.repository.pojo.TeamDetailResponse
import com.reachmobi.sports.repository.pojo.UpcomingEvents
import retrofit2.Response

class TeamsRepositoryImpl(private val teamsService: TeamsService) : TeamsRepository {
    override suspend fun getAllTeamsForLeague(leagueId: String) =
        teamsService.getAllTeamsForLeague(leagueId)

    override suspend fun getTeamDetails(teamId: String): Response<TeamDetailResponse> =
        teamsService.getTeamDetails(teamId)

    override suspend fun getTeamEvents(teamId: String): Response<UpcomingEvents> =
        teamsService.getUpcomingEventsForTeam(teamId)

}