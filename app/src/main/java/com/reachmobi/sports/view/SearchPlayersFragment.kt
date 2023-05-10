package com.reachmobi.sports.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.recyclerview.widget.GridLayoutManager
import com.reachmobi.sports.MainActivity
import com.reachmobi.sports.OnBoardingActivity
import com.reachmobi.sports.R
import com.reachmobi.sports.adapter.PlayersAdapter
import com.reachmobi.sports.databinding.SearchPlayersFragmentBinding
import com.reachmobi.sports.repository.pojo.AllPlayers
import com.reachmobi.sports.repository.viewstate.SearchPlayerViewState
import com.reachmobi.sports.viewmodel.SearchPlayerViewModel
import dagger.android.support.DaggerFragment
import javax.inject.Inject

class SearchPlayersFragment : DaggerFragment() {

    private lateinit var binding: SearchPlayersFragmentBinding

    @Inject
    lateinit var adapter: PlayersAdapter

    @Inject
    lateinit var viewModel: SearchPlayerViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = SearchPlayersFragmentBinding.inflate(inflater, container, false)
        binding.recyclerView.layoutManager = GridLayoutManager(this.requireContext(), 2)
        binding.recyclerView.adapter = adapter
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        (activity as MainActivity).supportActionBar?.title =
            resources.getText(R.string.all_players_search)
        binding.search.setOnClickListener {
            val searchText = binding.editText.text.toString()
            if (searchText.isNotEmpty()) {
                viewModel.getData(searchText)
            }
        }
    }

    override fun onResume() {
        super.onResume()

        viewModel.getTeamsLiveData().observe(viewLifecycleOwner) {
            when (it) {
                SearchPlayerViewState.Empty -> showEmpty()
                SearchPlayerViewState.Loading -> loading()
                is SearchPlayerViewState.Success -> showSuccess(it.allPlayers)
                is SearchPlayerViewState.Error -> showError()
            }
        }

    }

    private fun showSuccess(allPlayers: AllPlayers) {
        binding.progressBar.isVisible = false
        binding.errorImage.isVisible = false
        binding.errorTV.isVisible = false
        adapter.setData(allPlayers.player)
    }

    private fun loading() {
        binding.errorImage.isVisible = false
        binding.errorTV.isVisible = false
        binding.progressBar.isVisible = true
    }

    private fun showEmpty() {
        binding.progressBar.isVisible = false
        binding.errorTV.isVisible = true
        binding.errorTV.text = context?.resources?.getText(R.string.no_matching_results)
        binding.errorImage.isVisible = true
    }

    private fun showError() {
        binding.progressBar.isVisible = false
        binding.errorTV.isVisible = true
        binding.errorTV.text = context?.resources?.getText(R.string.something_went_wrong)
        binding.errorImage.isVisible = true
    }
}