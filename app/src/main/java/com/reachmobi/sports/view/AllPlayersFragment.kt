package com.reachmobi.sports.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.preference.PreferenceManager
import androidx.recyclerview.widget.GridLayoutManager
import com.reachmobi.sports.adapter.PlayersAdapter
import com.reachmobi.sports.databinding.PlayersFragmentBinding
import com.reachmobi.sports.repository.pojo.AllPlayers
import com.reachmobi.sports.repository.viewstate.PlayersViewState
import com.reachmobi.sports.viewmodel.PlayersViewModel
import dagger.android.support.DaggerFragment
import javax.inject.Inject

class AllPlayersFragment : DaggerFragment() {

    private lateinit var binding: PlayersFragmentBinding
    @Inject
    lateinit var adapter: PlayersAdapter
    @Inject
    lateinit var viewModel: PlayersViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = PlayersFragmentBinding.inflate(inflater, container, false)
        binding.recyclerView.layoutManager = GridLayoutManager(this.requireContext(), 2)
        binding.recyclerView.adapter = adapter

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.getAllTeamPlayers().observe(viewLifecycleOwner) {
            when (it) {
                PlayersViewState.Empty -> showEmpty()
                PlayersViewState.Loading -> loading()
                is PlayersViewState.Success -> showSuccess(it.allPlayers)
                is PlayersViewState.Error -> showError()
            }
        }
        val sharedPreferences = context?.let { PreferenceManager.getDefaultSharedPreferences(it) }
        val teamId = sharedPreferences?.getString("fav_team_id", null)
        teamId?.let { viewModel.getData(it) }

    }

    private fun showSuccess(allPlayers: AllPlayers) {
        binding.progressBar.isVisible = false
        adapter.setData(allPlayers.player)
    }

    private fun loading() {
        binding.errorImage.isVisible = false
        binding.progressBar.isVisible = true
    }

    private fun showEmpty() {
        binding.progressBar.isVisible = false
        binding.errorImage.isVisible = true
    }

    private fun showError() {
        binding.progressBar.isVisible = false
        binding.errorImage.isVisible = true
    }
}