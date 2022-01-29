package com.example.superheroapp.presentation

import android.content.Context
import android.content.res.Configuration
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.core.view.get
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.superheroapp.R
import com.example.superheroapp.databinding.FragmentHeroesListBinding
import com.example.superheroapp.di.appComponent
import com.example.superheroapp.util.HeroesResult
import dagger.Lazy
import javax.inject.Inject


class HeroesListFragment : Fragment() {

    @Inject
    lateinit var heroesViewModelFactory: Lazy<HeroesViewModel.Factory>
    private val viewModel: HeroesViewModel by viewModels { heroesViewModelFactory.get() }
    private val heroesListAdapter: HeroesListAdapter by lazy { HeroesListAdapter() }
    private var _binding: FragmentHeroesListBinding? = null
    private val bind get() = _binding!!

    override fun onAttach(context: Context) {
        context.appComponent.inject(this)
        super.onAttach(context)
    }

    override fun onResume() {
        super.onResume()
        val filterArray = resources.getStringArray(R.array.filter)
        val arrayAdapter = ArrayAdapter(requireContext(),R.layout.dropdown_item,filterArray)
        bind.autoCompleteTextView.setAdapter(arrayAdapter)
        bind.autoCompleteTextView.onItemClickListener = AdapterView.OnItemClickListener { adapterView, view, i, l ->
            val str = adapterView.getItemAtPosition(i).toString()
            val corStr = str.substring(0,str.length-1)
            Toast.makeText(requireContext(),"${corStr}.",Toast.LENGTH_SHORT).show()
            viewModel.publisherFlow.value = corStr
        }
    }


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        _binding = FragmentHeroesListBinding.inflate(inflater, container, false)



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
        return bind.root
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

}