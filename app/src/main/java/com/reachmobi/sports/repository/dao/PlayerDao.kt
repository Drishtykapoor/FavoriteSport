package com.reachmobi.sports.repository.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.reachmobi.sports.repository.pojo.Player

@Dao
interface PlayerDao {

    @Insert
    fun insert(player: Player)

    @Query("SELECT * FROM player")
    fun getAllPlayers(): LiveData<List<Player>>

}