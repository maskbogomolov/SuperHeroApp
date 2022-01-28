package com.example.superheroapp.presentation

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.ListAdapter
import com.example.superheroapp.databinding.HeroItemBinding
import com.example.superheroapp.domain.Heroes

class HeroesListAdapter : ListAdapter<Heroes,HeroesViewHolder>(HeroesDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HeroesViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = HeroItemBinding.inflate(layoutInflater,parent,false)
        return HeroesViewHolder(binding)
    }

    override fun onBindViewHolder(holder: HeroesViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    private class HeroesDiffCallback : DiffUtil.ItemCallback<Heroes>() {

        override fun areItemsTheSame(oldItem: Heroes, newItem: Heroes): Boolean =
            oldItem.id == newItem.id

        override fun areContentsTheSame(oldItem: Heroes, newItem: Heroes): Boolean =
            oldItem == newItem
    }


}