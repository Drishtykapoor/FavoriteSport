package com.reachmobi.sports.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.reachmobi.sports.repository.viewstate.TeamDetailViewState

interface MainActivityViewModel {
    fun getData()

    fun getWidgetLiveData(): MutableLiveData<Map<String, String>>
}
