package com.reachmobi.sports.repository

import javax.inject.Inject

class MainActivityRepositoryImpl @Inject constructor() : MainActivityRepository {

    override fun getFlags(): Map<String, String> {
        return mapOf(
            "imageView" to "true",
            "textView" to "false",
            "player_rounded_image_view" to "false",
            "player_square_image_view" to "true"
        )
    }

}