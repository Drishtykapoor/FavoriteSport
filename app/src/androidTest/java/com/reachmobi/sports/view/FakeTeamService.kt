package com.reachmobi.sports.view

import com.reachmobi.sports.repository.TeamsService
import com.reachmobi.sports.repository.pojo.*
import retrofit2.Response

class FakeTeamService : TeamsService {
    override suspend fun getAllTeamsForLeague(leagueId: String): Response<AllTeamsResponse> {
        return Response.success(
            AllTeamsResponse(
                listOf(
                    Teams(
                        strTeam = "some-name",
                        strTeamBadge = "some-url",
                        strDescriptionEN = "some-desccription"
                    )
                )
            )
        )
    }

    override suspend fun getTeamDetails(teamId: String): Response<TeamDetailResponse> {
        return Response.success(
            TeamDetailResponse(
                listOf(
                    Team(
                        strTeam = "some-name",
                        strTeamBadge = "some-url",
                        strDescriptionEN = "some-desccription"
                    )
                )
            )
        )
    }

    override suspend fun getUpcomingEventsForTeam(teamId: String): Response<UpcomingEvents> {
        return Response.success(UpcomingEvents(listOf()))
    }
}