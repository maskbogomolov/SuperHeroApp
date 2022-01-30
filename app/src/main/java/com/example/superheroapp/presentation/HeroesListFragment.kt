package com.example.superheroapp.presentation

import android.content.Context
import android.content.res.Configuration
import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.ThemeUtils
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.GridLayoutManager
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.example.superheroapp.R
import com.example.superheroapp.databinding.FragmentHeroesListBinding
import com.example.superheroapp.di.appComponent
import com.example.superheroapp.util.*
import com.example.superheroapp.util.Const.ALL_HEROES
import dagger.Lazy
import javax.inject.Inject
import kotlin.random.Random


class HeroesListFragment : Fragment() {

    @Inject
    lateinit var heroesViewModelFactory: Lazy<HeroesViewModel.Factory>
    private val viewModel: HeroesViewModel by viewModels { heroesViewModelFactory.get() }
    private val heroesListAdapter: HeroesListAdapter by lazy { HeroesListAdapter() }
    private var _binding: FragmentHeroesListBinding? = null
    private val bind get() = _binding!!
    @Inject
    lateinit var themeUtils: com.example.superheroapp.util.ThemeUtils

    override fun onAttach(context: Context) {
        context.appComponent.inject(this)
        super.onAttach(context)
    }

    override fun onResume() {
        super.onResume()
        val filterArray = resources.getStringArray(R.array.filter)
        val arrayAdapter = ArrayAdapter(requireContext(), R.layout.dropdown_item, filterArray)
        bind.autoCompleteTextView.setAdapter(arrayAdapter)
        bind.autoCompleteTextView.onItemClickListener = AdapterView.OnItemClickListener { adapterView, view, index, l ->
            val str = adapterView.getItemAtPosition(index)
            when (index) {
                0 -> viewModel.publisherFlow.value = ALL_HEROES
                else -> viewModel.publisherFlow.value = str.toString()
            }
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        _binding = FragmentHeroesListBinding.inflate(inflater, container, false)

        val span = when (resources.configuration.orientation) {
            Configuration.ORIENTATION_LANDSCAPE -> 2
            else -> 1
        }
        bind.heroesList.apply {
            adapter = heroesListAdapter
            layoutManager = GridLayoutManager(requireContext(), span)
        }
        viewModel.heroesResult.observe(viewLifecycleOwner, ::handleListHeroes)
        setHasOptionsMenu(true)
        setupToolbar()
        return bind.root
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.toolbar_menu, menu)
        menu.findItem(R.id.menu_toggle_theme).apply {
            val actionView = this.actionView
            if (actionView is ToggleThemeCheckBox) {
                actionView.isChecked = themeUtils.isDarkTheme(requireContext())
                actionView.setOnCheckedChangeListener { _, isChecked ->
                    themeUtils.setNightMode(isChecked, 1000L)
                }
            }
        }
    }

    private fun setupToolbar() {
        setHasOptionsMenu(true)
        (activity as AppCompatActivity).setSupportActionBar(bind.toolbar)
    }

    private fun handleListHeroes(state: HeroesResult) {
        when (state) {
            is HeroesResult.SuccessResult -> {
                heroesListAdapter.submitList(state.result)
                hideShimmerEffect()
            }
            is HeroesResult.Loading -> showShimmerEffect()
            is HeroesResult.ErrorResult -> {
                hideShimmerEffect()
                Toast.makeText(requireContext(), R.string.Error_message, Toast.LENGTH_LONG).show()
            }
        }
    }

    fun showShimmerEffect() {
        bind.heroesList.toGone()
        bind.shimmerFrameLayout.toVisible()
        bind.shimmerFrameLayout.startShimmer()
    }

    fun hideShimmerEffect() {
        bind.shimmerFrameLayout.toInvisible()
        bind.heroesList.toVisible()
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

}