package com.reachmobi.sports.viewmodel

import androidx.lifecycle.LiveData
import com.reachmobi.sports.repository.viewstate.AllTeamsViewState

interface AllTeamsViewModel {
    fun getData(teamsID: String)
    fun getTeamsLiveData(): LiveData<AllTeamsViewState>
}
