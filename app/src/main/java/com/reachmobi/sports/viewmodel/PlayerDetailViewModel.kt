package com.reachmobi.sports.viewmodel

import androidx.lifecycle.LiveData
import com.reachmobi.sports.repository.viewstate.PlayerDetailViewState

interface PlayerDetailViewModel {
    fun getData(playerName: String)
    fun getPlayerDetailLiveData(): LiveData<PlayerDetailViewState>
}
