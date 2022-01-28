package com.example.superheroapp.data

import com.example.superheroapp.data.module.HeroesDto
import retrofit2.http.GET

interface HeroesService {

    @GET("all.json")
    suspend fun getHeroes(): List<HeroesDto>
}