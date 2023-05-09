package com.reachmobi.sports.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.reachmobi.sports.adapter.AllLeagueListAdapter
import com.reachmobi.sports.databinding.SportsFragmentBinding
import com.reachmobi.sports.repository.AllLeagueViewState
import com.reachmobi.sports.repository.pojo.AllLeagues
import com.reachmobi.sports.viewmodel.AllLeagueViewModel
import dagger.android.support.DaggerFragment
import javax.inject.Inject

class AllLeagueFragment : DaggerFragment() {

    private lateinit var binding: SportsFragmentBinding
    @Inject
    lateinit var sportsAdapter: AllLeagueListAdapter
    @Inject
    lateinit var viewModel: AllLeagueViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = SportsFragmentBinding.inflate(inflater, container, false)
        binding.recyclerView.layoutManager = LinearLayoutManager(context)

        sportsAdapter = AllLeagueListAdapter(this.findNavController())
        binding.recyclerView.adapter = sportsAdapter
        return binding.root
    }

    override fun onResume() {
        super.onResume()

        viewModel.getSportsLiveData().observe(viewLifecycleOwner) {
            when (it) {
                AllLeagueViewState.Empty -> showEmpty()
                AllLeagueViewState.Loading -> showLoading()
                is AllLeagueViewState.Success -> showSuccess(it.allLeagues)
                is AllLeagueViewState.Error -> showError()
            }
        }
        viewModel.getData()
    }

    private fun showSuccess(allLeagues: AllLeagues) {
        binding.progressBar.isVisible = false
        sportsAdapter.setData(allLeagues.leagues)
    }

    private fun showLoading() {
        binding.progressBar.isVisible = true
        binding.errorImage.isVisible = false
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