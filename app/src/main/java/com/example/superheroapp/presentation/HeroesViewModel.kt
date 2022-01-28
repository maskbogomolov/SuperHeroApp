package com.example.superheroapp.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.liveData
import com.example.superheroapp.data.HeroesService
import javax.inject.Inject
import javax.inject.Provider

class HeroesViewModel(private val service: HeroesService): ViewModel() {

    val hero = liveData {
        val data = service.getHeroes()
        emit(data)
    }

    class Factory @Inject constructor(val api: Provider<HeroesService>) : ViewModelProvider.Factory {
        @Suppress("UNCHECKED_CAST")
        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            require(modelClass == HeroesViewModel::class.java)
            return HeroesViewModel(api.get()) as T
        }
    }
}