package com.reachmobi.sports.view

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.navigation.fragment.navArgs
import androidx.preference.PreferenceManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.reachmobi.sports.MainActivity
import com.reachmobi.sports.OnBoardingActivity
import com.reachmobi.sports.R
import com.reachmobi.sports.adapter.AllTeamsListAdapter
import com.reachmobi.sports.databinding.AllTeamsFragmentBinding
import com.reachmobi.sports.repository.pojo.AllTeamsResponse
import com.reachmobi.sports.repository.viewstate.AllTeamsViewState
import com.reachmobi.sports.viewmodel.AllTeamsViewModel
import dagger.android.support.DaggerFragment
import javax.inject.Inject

class AllTeamsFragment : DaggerFragment() {

    private lateinit var binding: AllTeamsFragmentBinding

    @Inject
    lateinit var teamsAdapter: AllTeamsListAdapter

    @Inject
    lateinit var teamsViewModel: AllTeamsViewModel

    private val args: AllTeamsFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = AllTeamsFragmentBinding.inflate(inflater, container, false)
        binding.recyclerView.layoutManager = LinearLayoutManager(this.requireContext())
        binding.recyclerView.adapter = teamsAdapter
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        (activity as OnBoardingActivity).supportActionBar?.title =
            resources.getText(R.string.select_fav_team)
        binding.trackteam.setOnClickListener {
            val teamID = teamsAdapter.getSelectedTeamId()
            if (teamID.isNullOrEmpty()) {
                Toast.makeText(context, "Invalid Selection", Toast.LENGTH_LONG).show()
            } else {
                val sharedPreferences =
                    context?.let { PreferenceManager.getDefaultSharedPreferences(it) }
                sharedPreferences?.edit()?.putString("fav_team_id", teamID)?.apply()
                context?.startActivity(Intent(context, MainActivity::class.java))
                activity?.finish()
            }

        }
    }

    override fun onResume() {
        super.onResume()

        teamsViewModel.getTeamsLiveData().observe(viewLifecycleOwner) {
            when (it) {
                AllTeamsViewState.Empty -> showEmpty()
                AllTeamsViewState.Loading -> showLoading()
                is AllTeamsViewState.Success -> showSuccess(it.allTeamsResponse)
                is AllTeamsViewState.Error -> showError()
            }
        }
        teamsViewModel.getData(args.leagueId)
    }

    private fun showSuccess(allTeamsResponse: AllTeamsResponse) {
        binding.progressBar.isVisible = false
        teamsAdapter.setData(allTeamsResponse.teams)
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