package com.reachmobi.sports.di

import com.google.firebase.analytics.ktx.analytics
import com.google.firebase.ktx.Firebase
import com.reachmobi.sports.util.FavSportsLogger
import com.reachmobi.sports.util.FavSportsLoggerImpl
import dagger.Binds
import dagger.Module
import dagger.Provides

@Module(includes = [LoggerModule.LoggerProvideModule::class])
interface LoggerModule {

    @Binds
    fun bindLogger(impl: FavSportsLoggerImpl): FavSportsLogger

    @Module
    object LoggerProvideModule {

        @Provides
        fun provideFirebaseAnalytics() = Firebase.analytics
    }
}