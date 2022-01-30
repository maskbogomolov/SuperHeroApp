package com.example.superheroapp.presentation

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.example.superheroapp.R
import com.example.superheroapp.databinding.FragmentHeroDetailsBinding
import com.example.superheroapp.di.appComponent
import com.example.superheroapp.util.HeroesResult
import com.example.superheroapp.util.NetworkResponse
import dagger.Lazy
import javax.inject.Inject

class HeroDetailsFragment : Fragment(R.layout.fragment_hero_details) {

    @Inject
    lateinit var heroViewModelFactory: Lazy<HeroDetailsViewModel.DetailsFactory>
    private val viewModel: HeroDetailsViewModel by viewModels { heroViewModelFactory.get() }
    private val args by navArgs<HeroDetailsFragmentArgs>()

    override fun onAttach(context: Context) {
        context.appComponent.inject(this)
        super.onAttach(context)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val id = args.id.toString()
        viewModel.loadListHeroes(id)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        val bind = FragmentHeroDetailsBinding.bind(view)
        viewModel.heroDetailsResult.observe(viewLifecycleOwner){
            when(it){
                is HeroesResult.SuccessDetailsResult -> bind.heroNameDetails.text = it.result.fullName
                is HeroesResult.ErrorResult -> Toast.makeText(requireContext(),"error",Toast.LENGTH_SHORT).show()
            }
        }
    }

}