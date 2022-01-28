package com.example.superheroapp.data

import com.example.superheroapp.data.heroes.RemoteDataSource
import com.example.superheroapp.domain.Heroes
import com.example.superheroapp.domain.HeroesRepository
import com.example.superheroapp.util.NetworkResponse
import com.example.superheroapp.util.mapSuccess
import javax.inject.Inject

class HeroesRepositoryImpl @Inject constructor(private val remoteDataSource: RemoteDataSource): HeroesRepository {

    override suspend fun getHeroes(): NetworkResponse<List<Heroes>, Throwable> {
        return remoteDataSource.getHeroes()
            .mapSuccess { heroesDto -> heroesDto.map { hero -> hero.toDomain() } }
    }
}