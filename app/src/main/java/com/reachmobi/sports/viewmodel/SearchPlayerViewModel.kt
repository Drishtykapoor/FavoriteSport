package com.reachmobi.sports.viewmodel

import androidx.lifecycle.LiveData
import com.reachmobi.sports.repository.viewstate.SearchPlayerViewState
import com.reachmobi.sports.repository.viewstate.TeamDetailViewState

interface SearchPlayerViewModel {
    fun getData(playerName: String)
    fun getTeamsLiveData(): LiveData<SearchPlayerViewState>
}
