package com.reachmobi.sports.view

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.LifecycleCoroutineScope

interface ViewPlugin {
    fun inflate(
        inflater: LayoutInflater,
        container: ViewGroup?,
        context: Context
    ): View

    fun fetchData(lifecycleScope: LifecycleCoroutineScope)

    fun getName(): String

    fun onPause()

    fun onDestroy()
}