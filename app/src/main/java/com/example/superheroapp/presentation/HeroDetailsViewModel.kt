package com.example.superheroapp.presentation

import androidx.lifecycle.*
import com.example.superheroapp.domain.HeroesRepository
import com.example.superheroapp.util.HeroesResult
import com.example.superheroapp.util.NetworkResponse
import kotlinx.coroutines.launch
import javax.inject.Inject
import javax.inject.Provider

class HeroDetailsViewModel(private val repo: HeroesRepository): ViewModel() {

    private val _heroDetailsResult = MutableLiveData<HeroesResult>()
    val heroDetailsResult get() = _heroDetailsResult

    fun loadListHeroes(id: String){
        viewModelScope.launch {
            handleDetailsHeroState(id)
        }
    }

    private suspend fun handleDetailsHeroState(id:String){
        when (val heroDetailsResult = repo.getDetails(id)) {
            is NetworkResponse.Error -> _heroDetailsResult.value = HeroesResult.ErrorResult(IllegalArgumentException("Heroes error"))
            is NetworkResponse.Success -> _heroDetailsResult.value = HeroesResult.SuccessDetailsResult(heroDetailsResult.result)
        }
    }

    class DetailsFactory @Inject constructor(val repo: Provider<HeroesRepository>) : ViewModelProvider.Factory {
        @Suppress("UNCHECKED_CAST")
        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            require(modelClass == HeroDetailsViewModel::class.java)
            return HeroDetailsViewModel(repo.get()) as T
        }
    }
}