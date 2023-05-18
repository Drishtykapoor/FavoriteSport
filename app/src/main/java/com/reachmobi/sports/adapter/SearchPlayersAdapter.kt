package com.reachmobi.sports.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.navigation.NavController
import androidx.recyclerview.widget.RecyclerView
import com.reachmobi.sports.R
import com.reachmobi.sports.repository.pojo.Player
import com.reachmobi.sports.util.FavSportsLogger
import com.reachmobi.sports.view.SearchPlayersFragmentDirections
import com.squareup.picasso.Picasso
import javax.inject.Inject

class SearchPlayersAdapter @Inject constructor(
    private val navController: NavController,
    private val logger: FavSportsLogger
) :
    RecyclerView.Adapter<SearchPlayersAdapter.ViewHolder>() {

    private val mydata = mutableListOf<Player>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.player_item_view, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.doSomething(mydata[position])
    }

    override fun getItemCount(): Int {
        return mydata.size
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        val name: TextView = itemView.findViewById(R.id.title)
        private val image: ImageView = itemView.findViewById(R.id.image)

        fun doSomething(sport: Player) {
            name.text = sport.strPlayer
            if (sport.strThumb.isNullOrEmpty()) image.setImageDrawable(
                ContextCompat.getDrawable(itemView.context, R.drawable.sports_generic)
            )
            else Picasso.with(itemView.context).load(sport.strThumb).into(image)
        }

        init {
            itemView.setOnClickListener {
                logger.logRowClick(
                    mydata[bindingAdapterPosition].strPlayer,
                    mydata[bindingAdapterPosition].idTeam ?: "No Team Found"
                )
                navController.navigate(
                    SearchPlayersFragmentDirections.actionSearchPlayersFragmentToPlayersDetailFragment(
                        mydata[bindingAdapterPosition].strPlayer
                    )
                )
            }
        }

    }

    fun setData(listSportsData: List<Player>) {
        val oldItemCount = mydata.size
        mydata.clear()
        notifyItemRangeRemoved(0, oldItemCount)
        mydata.addAll(listSportsData)
        notifyItemRangeInserted(0, listSportsData.size)
    }
}

