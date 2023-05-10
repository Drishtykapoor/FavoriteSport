package com.reachmobi.sports.util

interface FavSportsLogger {

    fun logButtonClick(name: String, value: String = "")
    fun logRowClick(name: String, value: String = "")
}