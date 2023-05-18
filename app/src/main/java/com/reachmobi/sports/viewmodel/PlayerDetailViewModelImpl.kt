package com.reachmobi.sports.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.reachmobi.sports.repository.PlayersRepository
import com.reachmobi.sports.repository.TeamsRepository
import com.reachmobi.sports.repository.viewstate.PlayerDetailViewState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class PlayerDetailViewModelImpl(private val teamsRepository: PlayersRepository) : ViewModel(),
    PlayerDetailViewModel {

    private val playerDetailLiveData = MutableLiveData<PlayerDetailViewState>()

    override fun getData(playerName: String) {
        playerDetailLiveData.value = PlayerDetailViewState.Loading

        viewModelScope.launch(Dispatchers.IO) {
            val response = teamsRepository.searchPlayer(playerName)
            withContext(Dispatchers.Main) {
                if (response.isSuccessful) {
                    if (response.body() != null) {
                        val data = response.body()!!.player
                        if (data.isEmpty()) {
                            playerDetailLiveData.value = PlayerDetailViewState.Empty
                        } else {
                            playerDetailLiveData.value =
                                PlayerDetailViewState.Success(response.body()!!)
                        }
                    } else playerDetailLiveData.value = PlayerDetailViewState.Empty
                } else {
                    playerDetailLiveData.value = PlayerDetailViewState.Error(response.message())
                }
            }
        }
    }

    override fun getPlayerDetailLiveData(): LiveData<PlayerDetailViewState> = playerDetailLiveData
}