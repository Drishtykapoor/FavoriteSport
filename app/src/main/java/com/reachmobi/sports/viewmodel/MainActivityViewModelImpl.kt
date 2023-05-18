package com.reachmobi.sports.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.reachmobi.sports.repository.MainActivityRepository
import kotlinx.coroutines.*
import kotlin.time.ExperimentalTime

@OptIn(ExperimentalTime::class)
class MainActivityViewModelImpl(private val mainActivityRepository: MainActivityRepository) : ViewModel(),
    MainActivityViewModel {

    private val flagData = MutableLiveData<Map<String, String>>()

    override fun getData() {
        viewModelScope.launch(Dispatchers.IO) {
            val response = mainActivityRepository.getFlags()
            withContext(Dispatchers.Main) {
                flagData.value = response
            }
        }
    }

    override fun getWidgetLiveData(): MutableLiveData<Map<String, String>> = flagData
}