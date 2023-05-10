package com.reachmobi.sports.util

import com.google.firebase.analytics.FirebaseAnalytics
import com.google.firebase.analytics.ktx.logEvent
import javax.inject.Inject

class FavSportsLoggerImpl @Inject constructor(val firebaseAnalytics: FirebaseAnalytics) :
    FavSportsLogger {

    override fun logButtonClick(name: String, value: String) {
        firebaseAnalytics.logEvent(FirebaseAnalytics.Event.SELECT_ITEM) {
            param(FirebaseAnalytics.Param.ITEM_NAME, "button : $name")
            param(FirebaseAnalytics.Param.ITEM_LIST_ID, value)
        }
    }

    override fun logRowClick(name: String, value: String) {
        firebaseAnalytics.logEvent(FirebaseAnalytics.Event.SELECT_ITEM) {
            param(FirebaseAnalytics.Param.ITEM_NAME, "row : $name")
            param(FirebaseAnalytics.Param.ITEM_LIST_ID, value)
        }
    }
}