package com.reachmobi.sports.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.ContextCompat
import androidx.navigation.NavController
import androidx.recyclerview.widget.RecyclerView
import com.reachmobi.sports.R
import com.reachmobi.sports.repository.pojo.Teams
import com.squareup.picasso.Picasso

class TeamDetailListAdapter(val navController: NavController) :
    RecyclerView.Adapter<TeamDetailListAdapter.ViewHolder>() {

    private var oldSelectedPosition: Int = -1
    private var selectedPosition: Int = -1
    private val mydata = mutableListOf<Teams>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {


        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.teams_item_view, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.doSomething(mydata[position], position)
    }

    override fun getItemCount(): Int {
        return mydata.size
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        val name: TextView = itemView.findViewById(R.id.title)
        private val body: ConstraintLayout = itemView.findViewById(R.id.body)
        private val league: TextView = itemView.findViewById(R.id.subtitle)
        private val image: ImageView = itemView.findViewById(R.id.image)

        fun doSomething(team: Teams, position: Int) {
            if (position == selectedPosition) {
                body.setBackgroundColor(
                    ContextCompat.getColor(itemView.context, R.color.dark_blue)
                )
            } else {
                body.setBackgroundColor(
                    ContextCompat.getColor(itemView.context, R.color.black)
                )
            }
            name.text = team.strTeam
            league.text = team.strLeague

            Picasso.with(itemView.context)
                .load(team.strTeamBadge)
                .fit()
                .into(image);
        }

        init {
            body.setOnClickListener {
                oldSelectedPosition = selectedPosition
                if (oldSelectedPosition != -1) {
                    selectedPosition = -1
                    notifyItemChanged(oldSelectedPosition)
                }
                selectedPosition = bindingAdapterPosition
                notifyItemChanged(selectedPosition)
            }
        }

    }

    fun setData(listTeamsData: List<Teams>) {
        mydata.clear()
        mydata.addAll(listTeamsData)
        notifyItemRangeInserted(0, listTeamsData.size)
    }

    fun getSelectedTeamId(): String? {
        return if (selectedPosition == -1) null else mydata[selectedPosition].idTeam
    }
}

