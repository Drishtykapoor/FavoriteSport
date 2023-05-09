package com.reachmobi.sports.viewmodel

import androidx.lifecycle.LiveData
import com.reachmobi.sports.repository.AllLeagueViewState

interface AllLeagueViewModel {
    fun getData()
    fun getSportsLiveData(): LiveData<AllLeagueViewState>
}