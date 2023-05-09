package com.reachmobi.sports.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.reachmobi.sports.repository.PlayersRepository

class SearchPlayerViewModelFactory(private val playersRepository: PlayersRepository) :
    ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return SearchPlayerViewModelImpl(playersRepository) as T
    }

}