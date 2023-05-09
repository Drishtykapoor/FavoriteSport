package com.reachmobi.sports.repository

import com.reachmobi.sports.repository.pojo.AllPlayers
import retrofit2.Response

interface PlayersRepository {
    suspend fun getAllTeamPlayers(teamId: String): Response<AllPlayers>
    suspend fun searchPlayer(teamId: String): Response<AllPlayers>
}