package com.reachmobi.sports.di

import android.content.Context
import com.reachmobi.sports.repository.dao.FavSportsDatabase
import com.reachmobi.sports.repository.dao.PlayerDao
import dagger.Module
import dagger.Provides

@Module
object DatabaseModule {

    @Provides
    fun providePlayerDB(context: Context): FavSportsDatabase {
        return FavSportsDatabase.getInstance(context)
    }

}