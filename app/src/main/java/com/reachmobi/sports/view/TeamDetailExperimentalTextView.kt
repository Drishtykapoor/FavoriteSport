package com.reachmobi.sports.view

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.lifecycle.LifecycleCoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject


class TeamDetailExperimentalTextView @Inject constructor() : ViewPlugin {

    var view: View? = null

    override fun inflate(inflater: LayoutInflater, container: ViewGroup?, context: Context): View {
        view = TextView(context).apply {

            val textParam = LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT,
                1.0f
            )

            layoutParams = textParam
        }
        return view!!
    }

    override fun fetchData(lifecycleScope: LifecycleCoroutineScope) {
        lifecycleScope.launch {
            withContext(Dispatchers.Main) {
                (view as TextView).text = "This is experimental container"
            }

        }
    }

    override fun getName(): String {
        return "textView"
    }

    override fun onPause() {

    }

    override fun onDestroy() {
        view = null
    }
}