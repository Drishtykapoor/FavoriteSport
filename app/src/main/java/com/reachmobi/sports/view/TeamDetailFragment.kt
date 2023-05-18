package com.reachmobi.sports.view

import android.content.SharedPreferences
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.gms.ads.AdRequest
import com.reachmobi.sports.adapter.TeamEventsAdapter
import com.reachmobi.sports.databinding.TeamDetailFragmentBinding
import com.reachmobi.sports.repository.viewstate.TeamDetailViewState
import com.reachmobi.sports.repository.viewstate.TeamEventViewState
import com.reachmobi.sports.viewmodel.TeamDetailViewModel
import com.squareup.picasso.Picasso
import javax.inject.Inject

open class TeamDetailFragment : FavSportsDaggerFragment() {

    private lateinit var binding: TeamDetailFragmentBinding

    @Inject
    lateinit var teamEventsAdapter: TeamEventsAdapter

    @Inject
    lateinit var viewModel: TeamDetailViewModel

    @Inject
    lateinit var viewPluginList: Array<ViewPlugin>

    @Inject
    lateinit var sharedPreferences: SharedPreferences


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = TeamDetailFragmentBinding.inflate(inflater, container, false)

        viewPluginList.forEach {
            if(isWidgetVisible(it)){
                context?.let { c ->
                    binding.experimentalLayout.addView(
                        it.inflate(
                            inflater,
                            container,
                            c
                        )
                    )
                }
            }
        }

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.recyclerView.layoutManager = LinearLayoutManager(context)
        binding.recyclerView.adapter = teamEventsAdapter

        val adRequest = AdRequest.Builder().build()
        binding.adView.loadAd(adRequest)
        val teamId = sharedPreferences.getString("fav_team_id", null)
        viewModel.getTeamsLiveData().observe(viewLifecycleOwner) {
            when (it) {
                TeamDetailViewState.Empty -> {
                    binding.eventsErrorView.isVisible = true
                }
                is TeamDetailViewState.Error -> {
                    binding.eventsErrorView.isVisible = true
                }
                TeamDetailViewState.Loading -> {
                    binding.eventsErrorView.isVisible = false

                }
                is TeamDetailViewState.Success -> {
                    binding.eventsErrorView.isVisible = false
                    val team = it.allTeamsResponse.teams[0]
                    Picasso.with(context)
                        .load(team.strTeamBadge)
                        .into(binding.image)
                    binding.title.text = team.strTeam
                    binding.description.text = team.strDescriptionEN
                }
            }
        }
        viewModel.getTeamEventsLiveData().observe(viewLifecycleOwner) {
            when (it) {
                TeamEventViewState.Empty -> {
                    binding.progressBar.isVisible = false
                    binding.eventsErrorView.isVisible = true
                }
                is TeamEventViewState.Error -> {
                    binding.progressBar.isVisible = false
                    binding.eventsErrorView.isVisible = true
                }
                TeamEventViewState.Loading -> {
                    binding.progressBar.isVisible = true
                    binding.eventsErrorView.isVisible = false
                }
                is TeamEventViewState.Success -> {
                    binding.progressBar.isVisible = false
                    binding.eventsErrorView.isVisible = false
                    teamEventsAdapter.setData(it.upcomingEvents.events)
                }
            }
        }

        teamId?.let { viewModel.getData(it) }
        teamId?.let { viewModel.getTeamEvents(it) }
    }

    private fun isWidgetVisible(viewPlugin: ViewPlugin): Boolean {
        val value = sharedPreferences.getString(viewPlugin.getName(), null)
        if(value!=null && value == "true"){
            return true
        }
        return false
    }
    override fun onResume() {
        super.onResume()
        viewPluginList.forEach {
            if(isWidgetVisible(it)){
                it.fetchData(lifecycleScope)
            }
        }
    }

    override fun onPause() {
        super.onPause()
        viewPluginList.forEach {
            if(isWidgetVisible(it)){
                it.onPause()
            }

        }
    }

    override fun onDestroy() {
        super.onDestroy()
        viewPluginList.forEach {
            if(isWidgetVisible(it)) {
                it.onDestroy()
            }
        }
    }
}