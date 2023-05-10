package com.reachmobi.sports.view

import android.os.Bundle
import android.view.*
import androidx.core.view.MenuHost
import androidx.core.view.MenuProvider
import androidx.lifecycle.Lifecycle
import androidx.navigation.fragment.findNavController
import com.google.android.material.tabs.TabLayoutMediator
import com.reachmobi.sports.MainActivity
import com.reachmobi.sports.R
import com.reachmobi.sports.adapter.HomeViewPagerAdapter
import com.reachmobi.sports.databinding.HomeFragmentBinding
import dagger.android.support.DaggerFragment
import javax.inject.Inject

class HomeFragment : DaggerFragment() {

    private lateinit var binding: HomeFragmentBinding

    @Inject
    lateinit var adapter: HomeViewPagerAdapter

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
        (activity as MainActivity).supportActionBar?.title =
            resources.getText(R.string.app_name)
        setUpSearchMenuItem(requireActivity())
        val pager = binding.viewPager
        val tab = binding.tabLayout

        // Initializing the ViewPagerAdapter
        pager.adapter = adapter
        // bind the viewPager with the TabLayout.
        TabLayoutMediator(tab, pager) { tab, position ->
            when (position) {
                0 -> tab.text = context?.resources?.getText(R.string.team_detail)
                else -> tab.text = context?.resources?.getText(R.string.team_players)
            }
        }.attach()
    }

    override fun onPause() {
        super.onPause()
        // this is a bug where it cant restore the state properly
        binding.run {
            viewPager.adapter = null
        }
    }

    private fun setUpSearchMenuItem(menuHost: MenuHost) {
        // Add menu items without using the Fragment Menu APIs
        // Note how we can tie the MenuProvider to the viewLifecycleOwner
        // and an optional Lifecycle.State (here, RESUMED) to indicate when
        // the menu should be visible
        menuHost.addMenuProvider(object : MenuProvider {
            override fun onCreateMenu(menu: Menu, menuInflater: MenuInflater) {
                // Add menu items here
                menuInflater.inflate(R.menu.menu, menu)
            }

            override fun onMenuItemSelected(menuItem: MenuItem): Boolean {
                // Handle the menu selection
                return when (menuItem.itemId) {
                    R.id.search -> {
                        findNavController().navigate(HomeFragmentDirections.actionHomeFragmentToSearchPlayersFragment())
                        true
                    }
                    else -> false
                }
            }
        }, viewLifecycleOwner, Lifecycle.State.CREATED)
    }
}
