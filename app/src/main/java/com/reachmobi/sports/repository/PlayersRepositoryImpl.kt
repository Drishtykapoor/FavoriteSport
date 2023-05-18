package com.reachmobi.sports.repository

import android.content.Context
import androidx.lifecycle.LiveData
import com.reachmobi.sports.repository.dao.PlayerDao
import com.reachmobi.sports.repository.dao.FavSportsDatabase
import com.reachmobi.sports.repository.pojo.AllPlayers
import com.reachmobi.sports.repository.pojo.Player
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.launch
import retrofit2.Response
import javax.inject.Inject

class PlayersRepositoryImpl @Inject constructor(
    private val sportsService: SportsService,
    private val playerDao: PlayerDao,
) :
    PlayersRepository {

    override suspend fun getAllTeamPlayers(teamId: String, pageNo: Int): Response<AllPlayers> {
        val response = sportsService.getAllTeamPlayers(teamId)
//        if (response.isSuccessful) {
//                clear table
//            response.body()!!.let {
//                it.player.forEach { player ->
//                    playerDao.insert(player)
//                }
//            }
//        }
        return response
    }


    override suspend fun searchPlayer(playerName: String) = sportsService.searchPlayer(playerName)
}