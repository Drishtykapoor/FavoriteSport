package com.reachmobi.sports.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.reachmobi.sports.repository.PlayersRepository
import com.reachmobi.sports.repository.viewstate.SearchPlayerViewState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class SearchPlayerViewModelImpl(private val playersRepository: PlayersRepository) : ViewModel(),
    SearchPlayerViewModel {

    private val teamsData = MutableLiveData<SearchPlayerViewState>()

    override fun getData(playerName: String) {
        teamsData.value = SearchPlayerViewState.Loading

        viewModelScope.launch {
            val response = playersRepository.searchPlayer(playerName)
            withContext(Dispatchers.Main) {
                if (response.isSuccessful) {
                    if (response.body() != null) {
                        val data = response.body()!!.player
                        if (data.isNullOrEmpty()) {
                            teamsData.value = SearchPlayerViewState.Empty
                        } else {
                            teamsData.value = SearchPlayerViewState.Success(response.body()!!)
                        }
                    } else teamsData.value = SearchPlayerViewState.Empty
                } else {
                    teamsData.value = SearchPlayerViewState.Error(response.message())
                }
            }
        }
    }

    override fun getTeamsLiveData(): LiveData<SearchPlayerViewState> = teamsData
}