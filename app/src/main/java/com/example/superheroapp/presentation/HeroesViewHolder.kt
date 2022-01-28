package com.example.superheroapp.presentation

import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.superheroapp.databinding.HeroItemBinding
import com.example.superheroapp.domain.Heroes

class HeroesViewHolder(
    private val binding: HeroItemBinding
): RecyclerView.ViewHolder(binding.root) {

    fun bind(data: Heroes){
        loadImage(data)
        setAliases(data)
        setName(data)
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
}