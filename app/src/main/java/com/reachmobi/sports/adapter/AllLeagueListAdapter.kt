package com.reachmobi.sports.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.navigation.NavController
import androidx.recyclerview.widget.RecyclerView
import com.reachmobi.sports.R
import com.reachmobi.sports.repository.pojo.League
import com.reachmobi.sports.view.AllLeagueFragmentDirections
import javax.inject.Inject

class AllLeagueListAdapter @Inject constructor(val navController: NavController) :
    RecyclerView.Adapter<AllLeagueListAdapter.ViewHolder>() {

    private val mydata = mutableListOf<League>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.league_item_view, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.doSomething(mydata[position])
    }

    override fun getItemCount(): Int {
        return mydata.size
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        val title: TextView = itemView.findViewById(R.id.title)
        private val subtitle: TextView = itemView.findViewById(R.id.subtitle)

        fun doSomething(sport: League) {
            title.text = sport.strLeague
            subtitle.text = sport.strSport
        }

        init {
            itemView.setOnClickListener {
                navController
                    .navigate(
                        AllLeagueFragmentDirections.detailAction(
                            mydata[bindingAdapterPosition].strLeague
                        ),
                    )
            }
        }

    }

    fun setData(listSportsData: List<League>) {
        mydata.clear()
        mydata.addAll(listSportsData)
        notifyDataSetChanged()
    }
}

