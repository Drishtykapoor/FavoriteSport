package com.reachmobi.sports.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.reachmobi.sports.databinding.PlayersDetailFragmentBinding

class PlayerDetailFragment: Fragment() {

    private lateinit var binding: PlayersDetailFragmentBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = PlayersDetailFragmentBinding.inflate(inflater,container,false)
        return binding.root
    }
}