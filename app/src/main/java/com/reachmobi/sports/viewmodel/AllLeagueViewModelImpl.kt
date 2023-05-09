package com.reachmobi.sports.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.reachmobi.sports.repository.LeagueRepository
import com.reachmobi.sports.repository.AllLeagueViewState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class AllLeagueViewModelImpl(private val leagueRepository: LeagueRepository): ViewModel(), AllLeagueViewModel {

    private val sportsData = MutableLiveData<AllLeagueViewState>()

    override fun getData() {
        sportsData.value = AllLeagueViewState.Loading

        viewModelScope.launch {
            val response = leagueRepository.getData()
            withContext(Dispatchers.Main){
                if(response.isSuccessful){
                    if(response.body()!=null){
                        val data = response.body()!!.leagues
                        if(data.isEmpty()){
                            sportsData.value = AllLeagueViewState.Empty
                        }
                        else{
                            sportsData.value = AllLeagueViewState.Success(response.body()!!)
                        }
                    }
                    else sportsData.value = AllLeagueViewState.Empty
                }
                else{
                    sportsData.value = AllLeagueViewState.Error(response.message())
                }
            }
        }
    }

    override fun getSportsLiveData(): LiveData<AllLeagueViewState> = sportsData

}