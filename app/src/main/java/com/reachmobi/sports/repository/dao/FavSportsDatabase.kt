package com.reachmobi.sports.repository.dao

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.reachmobi.sports.repository.pojo.Player

@Database(entities = [Player::class], version = 2)
abstract class FavSportsDatabase : RoomDatabase() {
    abstract fun playerDao(): PlayerDao

    companion object {
        private var instance: FavSportsDatabase? = null

        @Synchronized
        fun getInstance(ctx: Context): FavSportsDatabase {
            if (instance == null)
                instance = Room.databaseBuilder(
                    ctx.applicationContext,
                    FavSportsDatabase::class.java,
                    "player_database.db"
                )
                    .build()

            return instance!!
        }

    }
}