package com.example.superheroapp.util

import com.example.superheroapp.domain.HeroDetails
import com.example.superheroapp.domain.Heroes

sealed class HeroesResult{

    object Loading: HeroesResult()
    data class SuccessResult(val result: List<Heroes>) : HeroesResult()
    data class SuccessDetailsResult(val result: HeroDetails) : HeroesResult()
    data class ErrorResult(val e: Throwable) : HeroesResult()
}
