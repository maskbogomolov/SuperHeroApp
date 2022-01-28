package com.example.superheroapp.domain

import com.example.superheroapp.util.NetworkResponse

interface HeroesRepository {

    suspend fun getHeroes(): NetworkResponse<List<Heroes>, Throwable>
}