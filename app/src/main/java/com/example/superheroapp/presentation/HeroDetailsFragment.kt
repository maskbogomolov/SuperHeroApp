package com.example.superheroapp.presentation

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.superheroapp.R
import com.example.superheroapp.databinding.FragmentHeroDetailsBinding

class HeroDetailsFragment : Fragment(R.layout.fragment_hero_details) {


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        val bind = FragmentHeroDetailsBinding.bind(view)

    }

}