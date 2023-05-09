package com.reachmobi.sports.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.reachmobi.sports.repository.PlayersRepository
import com.reachmobi.sports.repository.viewstate.PlayersViewState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class PlayersViewModelImpl(private val playersRepository: PlayersRepository): ViewModel(), PlayersViewModel {

    private val sportsData = MutableLiveData<PlayersViewState>()

    override fun getData(teamId: String) {
        sportsData.value = PlayersViewState.Loading

        viewModelScope.launch {
            val response = playersRepository.getAllTeamPlayers(teamId)
            withContext(Dispatchers.Main){
                if(response.isSuccessful){
                    if(response.body()!=null){
                        val data = response.body()!!.player
                        if(data.isEmpty()){
                            sportsData.value = PlayersViewState.Empty
                        }
                        else{
                            sportsData.value = PlayersViewState.Success(response.body()!!)
                        }
                    }
                    else sportsData.value = PlayersViewState.Empty
                }
                else{
                    sportsData.value = PlayersViewState.Error(response.message())
                }
            }
        }
    }

    override fun getAllTeamPlayers(): LiveData<PlayersViewState> = sportsData

}