package com.example.superheroapp.domain

import com.example.superheroapp.data.heroes.HeroesEntity
import com.example.superheroapp.data.module.HeroDetailsDto
import com.example.superheroapp.util.NetworkResponse
import kotlinx.coroutines.flow.Flow

interface HeroesRepository {

    suspend fun getHeroes(): NetworkResponse<List<Heroes>, Throwable>
    suspend fun getHeroesByFilter(publisher: String): Flow<List<Heroes>>
    suspend fun getDetails(id: String): NetworkResponse<HeroDetails,Throwable>
}