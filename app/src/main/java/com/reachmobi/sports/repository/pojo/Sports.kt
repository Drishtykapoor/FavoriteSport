package com.reachmobi.sports.repository.pojo

import androidx.room.Entity
import androidx.room.PrimaryKey

data class Sports(
    var idSport : String = "",
    val strSport: String = "",
    val strFormat: String = "",
    val strSportThumb: String = "",
    val strSportIconGreen: String = "",
    val strSportDescription: String = ""
)