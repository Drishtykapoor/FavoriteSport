package com.reachmobi.sports.repository

import com.reachmobi.sports.repository.pojo.AllTeamsResponse
import com.reachmobi.sports.repository.pojo.TeamDetailResponse
import com.reachmobi.sports.repository.pojo.UpcomingEvents
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface TeamsService {

    @GET("api/v1/json/50130162/search_all_teams.php")
    suspend fun getAllTeamsForLeague(@Query("l") leagueId: String): Response<AllTeamsResponse>

    @GET("api/v1/json/50130162/lookupteam.php")
    suspend fun getTeamDetails(@Query("id") teamId: String): Response<TeamDetailResponse>

    @GET("api/v1/json/50130162/eventsnext.php?")
    suspend fun getUpcomingEventsForTeam(@Query("id") teamId: String): Response<UpcomingEvents>

}