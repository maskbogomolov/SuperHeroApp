package com.example.superheroapp.presentation

import androidx.lifecycle.*
import com.example.superheroapp.data.HeroesService
import com.example.superheroapp.domain.HeroesRepository
import com.example.superheroapp.util.HeroesResult
import com.example.superheroapp.util.NetworkResponse
import kotlinx.coroutines.launch
import javax.inject.Inject
import javax.inject.Provider

class HeroesViewModel(private val repository: HeroesRepository): ViewModel() {

    private val _heroesResult = MutableLiveData<HeroesResult>()
    val heroesResult get() = _heroesResult
//    val hero = liveData {
//        val data = repository.getHeroes()
//        emit(data)
//    }

    init {
        viewModelScope.launch {
            handleHeroesState()
        }
    }

    private suspend fun handleHeroesState(){
        _heroesResult.value = HeroesResult.Loading
         when (val heroesResult = repository.getHeroes()) {
            is NetworkResponse.Error -> _heroesResult.value = HeroesResult.ErrorResult(IllegalArgumentException("Heroes error"))
            is NetworkResponse.Success -> _heroesResult.value = HeroesResult.SuccessResult(heroesResult.result)
        }
    }

    class Factory @Inject constructor(val repository: Provider<HeroesRepository>) : ViewModelProvider.Factory {
        @Suppress("UNCHECKED_CAST")
        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            require(modelClass == HeroesViewModel::class.java)
            return HeroesViewModel(repository.get()) as T
        }
    }
}