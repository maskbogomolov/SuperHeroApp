package com.example.superheroapp.presentation

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import com.example.superheroapp.R
import com.example.superheroapp.databinding.FragmentHeroDetailsBinding
import com.example.superheroapp.di.appComponent
import com.example.superheroapp.util.NetworkResponse
import dagger.Lazy
import javax.inject.Inject

class HeroDetailsFragment : Fragment(R.layout.fragment_hero_details) {

    @Inject
    lateinit var heroesViewModelFactory: Lazy<HeroesViewModel.Factory>
    private val viewModel: HeroesViewModel by viewModels { heroesViewModelFactory.get() }

    override fun onAttach(context: Context) {
        context.appComponent.inject(this)
        super.onAttach(context)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        val bind = FragmentHeroDetailsBinding.bind(view)
        viewModel.livedata.observe(viewLifecycleOwner){
            when(it){
                is NetworkResponse.Success -> bind.heroNameDetails.text = it.result.fullName
                is NetworkResponse.Error -> Toast.makeText(requireContext(),"error",Toast.LENGTH_SHORT).show()
            }
        }
    }

}