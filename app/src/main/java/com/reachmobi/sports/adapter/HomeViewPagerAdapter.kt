package com.reachmobi.sports.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.reachmobi.sports.view.AllPlayersFragment
import com.reachmobi.sports.view.TeamDetailFragment

class HomeViewPagerAdapter(fragment: FragmentActivity) :
    FragmentStateAdapter(fragment) {

    private val mDataSet = listOf<Fragment>(TeamDetailFragment(),
        AllPlayersFragment()
    )

    override fun getItemCount(): Int = 2

    override fun createFragment(position: Int): Fragment {
        return when (position) {
            0 -> mDataSet[0]
            else -> mDataSet[1]
        }
    }
}