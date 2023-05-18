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
import com.reachmobi.sports.view.AllPlayersFragmentInterface
import com.reachmobi.sports.view.HomeFragmentDirections
import com.squareup.picasso.Picasso
import javax.inject.Inject

class PlayersAdapter @Inject constructor(
    private val navController: NavController,
    private val logger: FavSportsLogger
) :
    RecyclerView.Adapter<PlayersAdapter.PlayerImageShapeViewHolder>() {

    private val mydata = mutableListOf<Player>()
    private var pageIndex = 0
    lateinit var allPlayersFragmentInterface: AllPlayersFragmentInterface
    private var isRounded = true

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PlayerImageShapeViewHolder {
        return when (viewType){
        0 -> PlayerRoundedImageViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.player_item_view, parent, false))
        else -> PlayerSquareImageViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.player_square_item_view, parent, false))
    }
}

    override fun onBindViewHolder(holder: PlayerImageShapeViewHolder, position: Int) {
        holder.doSomething(mydata[position])

        if(mydata.size - position == 3) {
            pageIndex++
            allPlayersFragmentInterface.loadDataForPage(pageIndex)
        }
    }

    override fun getItemViewType(position: Int): Int {
        return if (isRounded){
            0
        } else 1
    }

    override fun getItemCount(): Int {
        return mydata.size
    }

    abstract class PlayerImageShapeViewHolder(open val view: View) : RecyclerView.ViewHolder(view) {
        abstract fun doSomething(sport: Player)
    }

    inner class PlayerRoundedImageViewHolder(itemView: View) : PlayerImageShapeViewHolder(itemView) {

        val name: TextView = itemView.findViewById(R.id.title)
        private val image: ImageView = itemView.findViewById(R.id.image)

        override fun doSomething(sport: Player) {
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
                    mydata[bindingAdapterPosition].idTeam?: "Team Not Found"
                )
                navController.navigate(
                    HomeFragmentDirections.actionHomeFragmentToPlayersDetailFragment(
                        mydata[bindingAdapterPosition].strPlayer
                    )
                )
            }
        }

    }

    inner class PlayerSquareImageViewHolder(itemView: View) : PlayerImageShapeViewHolder(itemView) {

        val name: TextView = itemView.findViewById(R.id.title)
        private val image: ImageView = itemView.findViewById(R.id.image)

        override fun doSomething(sport: Player) {
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
                    mydata[bindingAdapterPosition].idTeam ?: "Team Not Found"
                )
                navController.navigate(
                    HomeFragmentDirections.actionHomeFragmentToPlayersDetailFragment(
                        mydata[bindingAdapterPosition].strPlayer
                    )
                )
            }
        }

    }


    //    data class PlayerResponse(val pageid: Int, val listSportsData: List<Player>){
//
//    }
    fun setData(listSportsData: List<Player>, rounded: Boolean) {

        val oldItemCount = mydata.size
        isRounded = rounded

        mydata.addAll(listSportsData)
        notifyItemRangeInserted(oldItemCount, mydata.size-1)

        if(oldItemCount==mydata.size){
            pageIndex--
        }

        //mydata.clear()
        //notifyItemRangeRemoved(0, oldItemCount)
        //notifyItemInserted(currentPos)
    }

    fun setCallBackForPagination(playerInterface: AllPlayersFragmentInterface){
        allPlayersFragmentInterface = playerInterface
    }
}

