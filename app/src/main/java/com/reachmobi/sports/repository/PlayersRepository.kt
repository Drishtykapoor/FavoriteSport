package com.reachmobi.sports.repository

import android.content.Context
import androidx.lifecycle.LiveData
import com.reachmobi.sports.repository.pojo.AllPlayers
import com.reachmobi.sports.repository.pojo.Player
import retrofit2.Response

interface PlayersRepository {
    suspend fun getAllTeamPlayers(teamId: String, pageNo: Int): Response<AllPlayers>
    suspend fun searchPlayer(teamId: String): Response<AllPlayers>

    //suspend fun getAllPlayerData(context: Context): LiveData<List<Player>>
}