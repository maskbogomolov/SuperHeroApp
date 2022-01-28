package com.example.superheroapp.presentation

import android.content.Context
import android.content.res.Configuration
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.superheroapp.R
import com.example.superheroapp.databinding.FragmentHeroesListBinding
import com.example.superheroapp.di.appComponent
import com.example.superheroapp.util.HeroesResult
import dagger.Lazy
import javax.inject.Inject


class HeroesListFragment : Fragment(R.layout.fragment_heroes_list) {

    @Inject
    lateinit var heroesViewModelFactory: Lazy<HeroesViewModel.Factory>
    private val viewModel: HeroesViewModel by viewModels { heroesViewModelFactory.get() }
    private val heroesListAdapter: HeroesListAdapter by lazy { HeroesListAdapter() }

    override fun onAttach(context: Context) {
        context.appComponent.inject(this)
        super.onAttach(context)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        val bind = FragmentHeroesListBinding.bind(view)

        val span = when(resources.configuration.orientation){
            Configuration.ORIENTATION_LANDSCAPE -> 2
            else -> 1
        }
        bind.heroesList.apply {
            adapter = heroesListAdapter
            layoutManager = GridLayoutManager(requireContext(),span)
        }

        viewModel.heroesResult.observe(viewLifecycleOwner){
            when(it){
                is HeroesResult.SuccessResult -> heroesListAdapter.submitList(it.result)
            }
        }
    }

}