package com.reachmobi.sports.repository

class LeagueRepositoryImpl(private val sportsService: SportsService) : LeagueRepository {
    override suspend fun getData() = sportsService.getAllLeagues()

}