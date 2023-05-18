package com.reachmobi.sports.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.reachmobi.sports.repository.MainActivityRepository

class MainActivityViewModelFactory (private val mainActivityRepository: MainActivityRepository): ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return MainActivityViewModelImpl(mainActivityRepository) as T
    }

}