package com.reachmobi.sports.view

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.google.android.material.tabs.TabLayoutMediator
import com.reachmobi.sports.R
import com.reachmobi.sports.adapter.HomeViewPagerAdapter
import com.reachmobi.sports.databinding.HomeFragmentBinding
import dagger.android.support.DaggerFragment
import javax.inject.Inject

class HomeFragment : DaggerFragment() {

    private lateinit var binding: HomeFragmentBinding
    @Inject lateinit var adapter: HomeViewPagerAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = HomeFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val pager = binding.viewPager
        val tab = binding.tabLayout

        // Initializing the ViewPagerAdapter
        pager.adapter = adapter
        // bind the viewPager with the TabLayout.
        TabLayoutMediator(tab, pager) { tab, position ->
            when (position) {
                0 -> tab.text = "Sports"
                else -> tab.text = "Players"
            }
        }.attach()
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.menu, menu)
        val search = menu.findItem(R.id.appSearchBar)
        search.setOnMenuItemClickListener {
            findNavController().navigate(HomeFragmentDirections.actionHomeFragmentToSearchPlayersFragment())
            true
        }
    }

}