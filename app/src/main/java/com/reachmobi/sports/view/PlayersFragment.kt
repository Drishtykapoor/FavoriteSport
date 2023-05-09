package com.reachmobi.sports.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.preference.PreferenceManager
import androidx.recyclerview.widget.GridLayoutManager
import com.reachmobi.sports.adapter.PlayersAdapter
import com.reachmobi.sports.databinding.PlayersFragmentBinding
import com.reachmobi.sports.viewmodel.PlayersViewModelFactory
import com.reachmobi.sports.viewmodel.PlayersViewModelImpl
import com.reachmobi.sports.repository.*
import com.reachmobi.sports.repository.pojo.AllPlayers
import com.reachmobi.sports.repository.viewstate.PlayersViewState

class PlayersFragment: Fragment() {

    private lateinit var binding: PlayersFragmentBinding
    lateinit var adapter: PlayersAdapter
    lateinit var viewModel: PlayersViewModelImpl
    lateinit var playersRepository: PlayersRepositoryImpl
    lateinit var retrofitClient: RetrofitClient
    lateinit var playersViewModelFactory: PlayersViewModelFactory

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = PlayersFragmentBinding.inflate(inflater, container, false)
        binding.recyclerView.layoutManager = GridLayoutManager(this.requireContext(), 2)

        adapter = PlayersAdapter(this.findNavController())
        binding.recyclerView.adapter = adapter

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        retrofitClient = RetrofitClient
        val sportsService: SportsService = context?.let { retrofitClient.getRetrofitInstance(it) }!!.create(
            SportsService::class.java
        )
        playersRepository = PlayersRepositoryImpl(sportsService)

        playersViewModelFactory = PlayersViewModelFactory(playersRepository)
        viewModel =
            ViewModelProvider(this, playersViewModelFactory).get(PlayersViewModelImpl::class.java)
    }

    override fun onResume() {
        super.onResume()

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