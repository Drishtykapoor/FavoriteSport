package com.reachmobi.sports.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.reachmobi.sports.databinding.PlayersDetailFragmentBinding
import com.reachmobi.sports.repository.viewstate.PlayerDetailViewState
import com.reachmobi.sports.viewmodel.PlayerDetailViewModel
import com.squareup.picasso.Picasso
import dagger.android.support.DaggerFragment
import javax.inject.Inject

class PlayerDetailFragment: DaggerFragment() {

    private lateinit var binding: PlayersDetailFragmentBinding

    @Inject
    lateinit var viewModel: PlayerDetailViewModel

    private val args: PlayerDetailFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = PlayersDetailFragmentBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.getPlayerDetailLiveData().observe(viewLifecycleOwner) {
            when (it) {
                PlayerDetailViewState.Empty -> {
                    binding.error.isVisible = true
                }
                is PlayerDetailViewState.Error -> {
                    binding.error.isVisible = true
                }
                PlayerDetailViewState.Loading -> {
                    binding.error.isVisible = false
                }
                is PlayerDetailViewState.Success -> {
                    binding.error.isVisible = false
                    val player = it.allTeamsResponse.player[0]
                    Picasso.with(context)
                        .load(player.strThumb)
                        .into(binding.image)
                    binding.title.text = player.strPlayer
                    binding.description.text = player.strDescriptionEN
                }
            }
        }
    }

    override fun onResume() {
        super.onResume()
        viewModel.getData(args.name)
    }
}