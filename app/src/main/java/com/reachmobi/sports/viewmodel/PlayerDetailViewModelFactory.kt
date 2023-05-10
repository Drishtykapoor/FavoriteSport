package com.reachmobi.sports.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.reachmobi.sports.repository.PlayersRepository

class PlayerDetailViewModelFactory(private val playersRepository: PlayersRepository) :
    ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return PlayerDetailViewModelImpl(playersRepository) as T
    }
}