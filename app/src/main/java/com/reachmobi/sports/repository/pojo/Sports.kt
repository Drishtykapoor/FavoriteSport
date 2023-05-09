package com.reachmobi.sports.repository.pojo

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Sports(
    @PrimaryKey(autoGenerate = true)
    var idSport : String = "",
    val strSport: String = "",
    val strFormat: String = "",
    val strSportThumb: String = "",
    val strSportIconGreen: String = "",
    val strSportDescription: String = ""
)