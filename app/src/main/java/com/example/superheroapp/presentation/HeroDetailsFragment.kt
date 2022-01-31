package com.example.superheroapp.presentation

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import coil.load
import com.example.superheroapp.R
import com.example.superheroapp.databinding.FragmentHeroDetailsBinding
import com.example.superheroapp.databinding.FragmentHeroesListBinding
import com.example.superheroapp.di.appComponent
import com.example.superheroapp.util.HeroesResult
import com.example.superheroapp.util.NetworkResponse
import com.example.superheroapp.util.toInvisible
import com.example.superheroapp.util.toVisible
import dagger.Lazy
import javax.inject.Inject

class HeroDetailsFragment : Fragment(R.layout.fragment_hero_details) {

    @Inject
    lateinit var heroViewModelFactory: Lazy<HeroDetailsViewModel.DetailsFactory>
    private val viewModel: HeroDetailsViewModel by viewModels { heroViewModelFactory.get() }
    private val args by navArgs<HeroDetailsFragmentArgs>()
    private var _binding: FragmentHeroDetailsBinding? = null
    private val bind get() = _binding!!

    override fun onAttach(context: Context) {
        context.appComponent.inject(this)
        super.onAttach(context)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val id = args.id.toString()
        viewModel.loadListHeroes(id)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        _binding = FragmentHeroDetailsBinding.inflate(inflater, container, false)
        viewModel.heroDetailsResult.observe(viewLifecycleOwner,::handleHeroDetails)
        bind.toolbar.setNavigationOnClickListener { findNavController().popBackStack() }
        return bind.root
    }

    private fun handleHeroDetails(state: HeroesResult){
        when(state){
            is HeroesResult.SuccessDetailsResult -> {
                bind.heroNameDetails.text = state.result.fullName
                bind.heroAliasesDetails.text = state.result.aliases
                bind.alterEgosTxt.text = state.result.alterEgos
                bind.placeOfBirthTxt.text = state.result.placeOfBirth
                bind.firstAppearanceTxt.text = state.result.firstAppearance
                bind.holderHeroInfo.toVisible()
                if (args.publisherLogo.isNotEmpty()) bind.publisherLogo.load(args.publisherLogo)
                bind.heroImageDetails.load(args.image)
            }
            is HeroesResult.ErrorResult -> {
                Toast.makeText(requireContext(),R.string.Error_message,Toast.LENGTH_LONG).show()
                bind.holderHeroInfo.toInvisible()
            }
        }
    }

}