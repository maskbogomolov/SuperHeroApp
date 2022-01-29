package com.example.superheroapp.data.heroes

import com.example.superheroapp.data.module.HeroesDto
import retrofit2.http.GET
import retrofit2.http.Path

interface HeroesService {

    @GET("all.json")
    suspend fun getHeroes(): List<HeroesDto>

    @GET("/{id}/biography")
    suspend fun getDetails(
        @Path("id") id: String
    ): HeroDetailsDto
}