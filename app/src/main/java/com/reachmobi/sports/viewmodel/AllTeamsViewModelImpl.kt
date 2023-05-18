package com.reachmobi.sports.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.reachmobi.sports.repository.TeamsRepository
import com.reachmobi.sports.repository.viewstate.AllTeamsViewState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class AllTeamsViewModelImpl (private val teamsRepository: TeamsRepository): ViewModel(), AllTeamsViewModel {

    private val teamsData = MutableLiveData<AllTeamsViewState>()

    override fun getData(leagueId: String) {
        teamsData.value = AllTeamsViewState.Loading

        viewModelScope.launch(Dispatchers.IO) {
            val response = teamsRepository.getAllTeamsForLeague(leagueId)
            withContext(Dispatchers.Main){
                if(response.isSuccessful){
                    if(response.body()!=null){
                        val data = response.body()!!.teams
                        if(data.isEmpty()){
                            teamsData.value = AllTeamsViewState.Empty
                        }
                        else{
                            teamsData.value = AllTeamsViewState.Success(response.body()!!)
                        }
                    }
                    else teamsData.value = AllTeamsViewState.Empty
                }
                else{
                    teamsData.value = AllTeamsViewState.Error(response.message())
                }
            }
        }
    }

    override fun getTeamsLiveData(): LiveData<AllTeamsViewState> = teamsData

    override fun getPageNoData(pageNo: Int) {
        TODO("Not yet implemented")
    }
}