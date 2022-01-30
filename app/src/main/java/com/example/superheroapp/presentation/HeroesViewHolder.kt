package com.example.superheroapp.presentation

import android.view.View
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.superheroapp.databinding.HeroItemBinding
import com.example.superheroapp.domain.Heroes
import com.example.superheroapp.util.Const.ABC_STUDIOS_COMICS
import com.example.superheroapp.util.Const.ABC_STUDIOS_COMICS_IMAGE_URL
import com.example.superheroapp.util.Const.DARK_HORSE_COMICS
import com.example.superheroapp.util.Const.DARK_HORSE_COMICS_IMAGE_URL
import com.example.superheroapp.util.Const.DC_COMICS
import com.example.superheroapp.util.Const.DC_COMICS_IMAGE_URL
import com.example.superheroapp.util.Const.GEORGE_LUCAS_COMICS
import com.example.superheroapp.util.Const.GEORGE_LUCAS_COMICS_IMAGE_URL
import com.example.superheroapp.util.Const.MARVEL_COMICS
import com.example.superheroapp.util.Const.MARVEL_COMICS_IMAGE_URL
import com.example.superheroapp.util.Const.NBC_COMICS
import com.example.superheroapp.util.Const.NBC_COMICS_IMAGE_URL

class HeroesViewHolder(
    private val binding: HeroItemBinding
): RecyclerView.ViewHolder(binding.root) {

    private val holderStudioImages = mapOf<String,String>(
        MARVEL_COMICS to MARVEL_COMICS_IMAGE_URL,
        DC_COMICS to DC_COMICS_IMAGE_URL,
        DARK_HORSE_COMICS to DARK_HORSE_COMICS_IMAGE_URL,
        GEORGE_LUCAS_COMICS to GEORGE_LUCAS_COMICS_IMAGE_URL,
        ABC_STUDIOS_COMICS to ABC_STUDIOS_COMICS_IMAGE_URL,
        NBC_COMICS to NBC_COMICS_IMAGE_URL
        // и т.д
    )

    fun bind(data: Heroes){
        loadImage(data)
        setAliases(data)
        setName(data)
        loadImage(data)
        loadStudioImage(data)
        itemView.setOnClickListener {
            navigateToDetails(data,it)
        }
    }
    fun navigateToDetails(data: Heroes,view: View) {
        var logo = ""
        holderStudioImages.takeIf { it.containsKey(data.publisher) }?.apply {
            logo = get(data.publisher).toString()
        }
        val direction = HeroesListFragmentDirections
            .actionHeroesListFragmentToHeroDetailsFragment(data.id,data.images,logo)
        view.findNavController().navigate(direction)
    }

    fun loadImage(data: Heroes){
        binding.heroImage.load(data.images)
    }

    fun setAliases(data: Heroes){
        binding.heroAliases.text = data.aliases
    }

    fun setName(data: Heroes){
        binding.heroName.text = data.name
    }
    fun loadStudioImage(data: Heroes){
        holderStudioImages.takeIf { it.containsKey(data.publisher) }?.apply {
            binding.studioImage.load(get(data.publisher))
        }
    }

}