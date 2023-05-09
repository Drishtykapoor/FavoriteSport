package com.reachmobi.sports.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.reachmobi.sports.R
import com.reachmobi.sports.repository.pojo.Event
import com.squareup.picasso.Picasso
import javax.inject.Inject

class TeamEventsAdapter @Inject constructor() :
    RecyclerView.Adapter<TeamEventsAdapter.ViewHolder>() {

    private val teamEventsDataSet = mutableListOf<Event>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view =
            LayoutInflater.from(parent.context)
                .inflate(R.layout.team_events_item_view, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.doSomething(teamEventsDataSet[position])
    }

    override fun getItemCount(): Int {
        return teamEventsDataSet.size
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        val title: TextView = itemView.findViewById(R.id.title)
        val subtitle: TextView = itemView.findViewById(R.id.subtitle)
        private val image: ImageView = itemView.findViewById(R.id.image)

        fun doSomething(event: Event) {
            title.text = event.strEvent
            subtitle.text = event.strLeague
            if(!event.strThumb.isNullOrEmpty())
                Picasso.with(itemView.context)
                    .load(event.strThumb)
                    .into(image)
        }
    }

    fun setData(events: List<Event>) {
        val oldItemCount = teamEventsDataSet.size
        teamEventsDataSet.clear()
        notifyItemRangeRemoved(0, oldItemCount);
        teamEventsDataSet.addAll(events)
        notifyItemRangeInserted(0, events.size)
    }
}

