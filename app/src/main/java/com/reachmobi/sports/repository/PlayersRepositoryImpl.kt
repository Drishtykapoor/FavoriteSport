package com.reachmobi.sports.repository

import com.reachmobi.sports.repository.pojo.AllPlayers
import retrofit2.Response

class PlayersRepositoryImpl(private val sportsService: SportsService) : PlayersRepository {
    override suspend fun getAllTeamPlayers(teamId: String) = sportsService.getAllTeamPlayers(teamId)
    override suspend fun searchPlayer(playerName: String) = sportsService.searchPlayer(playerName)
}