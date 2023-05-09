package com.reachmobi.sports.repository

import com.reachmobi.sports.repository.pojo.AllLeagues
import com.reachmobi.sports.repository.pojo.AllPlayers
import com.reachmobi.sports.repository.pojo.SportsResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface SportsService {
    @GET("api/v1/json/50130162/all_leagues.php")
    suspend fun getAllLeagues(): Response<AllLeagues>

    @GET("api/v1/json/50130162/lookup_all_players.php")
    suspend fun getAllTeamPlayers(@Query("id") teamId: String): Response<AllPlayers>

    @GET("api/v1/json/3/searchplayers.php?")
    suspend fun searchPlayer(@Query("p") playerName: String): Response<AllPlayers>

}