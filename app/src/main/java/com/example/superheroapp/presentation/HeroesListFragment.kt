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
import com.example.superheroapp.databinding.FragmentHeroesListBinding
import com.example.superheroapp.di.appComponent
import dagger.Lazy
import javax.inject.Inject


class HeroesListFragment : Fragment(R.layout.fragment_heroes_list) {

    @Inject
    lateinit var heroesViewModelFactory: Lazy<HeroesViewModel.Factory>
    private val viewModel: HeroesViewModel by viewModels { heroesViewModelFactory.get() }

    override fun onAttach(context: Context) {
        context.appComponent.inject(this)
        super.onAttach(context)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        val bind = FragmentHeroesListBinding.bind(view)
        viewModel.hero.observe(viewLifecycleOwner){
            Toast.makeText(requireContext(),"${it[0].name}",Toast.LENGTH_SHORT).show()
        }
    }

}