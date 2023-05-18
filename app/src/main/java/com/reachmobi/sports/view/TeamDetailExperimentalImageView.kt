package com.reachmobi.sports.view

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import androidx.lifecycle.LifecycleCoroutineScope
import com.squareup.picasso.Picasso
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject


class TeamDetailExperimentalImageView @Inject constructor() : ViewPlugin {

    var view: View? = null

    override fun inflate(inflater: LayoutInflater, container: ViewGroup?, context: Context): View {
        view = ImageView(context).apply {

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
                Picasso.with(view?.context)
                    .load("https://www.vhv.rs/dpng/d/542-5422700_ms-dhoni-png-image-free-download-searchpng-csk.png")
                    .into(view as ImageView)
                //(view as TextView).text = "This is experimental container"
            }

        }
    }

    override fun getName(): String {
        return "imageView"
    }

    override fun onPause() {}

    override fun onDestroy() {
        view = null
    }
}