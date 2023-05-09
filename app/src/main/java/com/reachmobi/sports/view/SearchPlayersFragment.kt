package com.reachmobi.sports.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.reachmobi.sports.R
import com.reachmobi.sports.adapter.PlayersAdapter
import com.reachmobi.sports.databinding.SearchPlayersFragmentBinding
import com.reachmobi.sports.repository.PlayersRepositoryImpl
import com.reachmobi.sports.repository.RetrofitClient
import com.reachmobi.sports.repository.SportsService
import com.reachmobi.sports.repository.pojo.AllPlayers
import com.reachmobi.sports.repository.viewstate.SearchPlayerViewState
import com.reachmobi.sports.viewmodel.SearchPlayerViewModelFactory
import com.reachmobi.sports.viewmodel.SearchPlayerViewModelImpl

class SearchPlayersFragment : Fragment() {

    private lateinit var binding: SearchPlayersFragmentBinding
    lateinit var adapter: PlayersAdapter
    private lateinit var viewModel: SearchPlayerViewModelImpl
    lateinit var playersRepository: PlayersRepositoryImpl
    lateinit var retrofitClient: RetrofitClient
    lateinit var playersViewModelFactory: SearchPlayerViewModelFactory

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = SearchPlayersFragmentBinding.inflate(inflater, container, false)
        binding.recyclerView.layoutManager = GridLayoutManager(this.requireContext(), 2)

        adapter = PlayersAdapter(this.findNavController())
        binding.recyclerView.adapter = adapter

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        retrofitClient = RetrofitClient
        val sportsService: SportsService =
            context?.let { retrofitClient.getRetrofitInstance(it) }!!.create(
                SportsService::class.java
            )
        playersRepository = PlayersRepositoryImpl(sportsService)

        playersViewModelFactory = SearchPlayerViewModelFactory(playersRepository)
        viewModel =
            ViewModelProvider(
                this,
                playersViewModelFactory
            ).get(SearchPlayerViewModelImpl::class.java)
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