package com.reachmobi.sports.repository

import com.reachmobi.sports.repository.pojo.AllLeagues
import retrofit2.Response

interface LeagueRepository {
    suspend fun getData(): Response<AllLeagues>
}