package com.example.superheroapp.data

import com.example.superheroapp.data.heroes.LocalDataSource
import com.example.superheroapp.data.heroes.RemoteDataSource
import com.example.superheroapp.data.heroes.toEntity
import com.example.superheroapp.domain.Heroes
import com.example.superheroapp.domain.HeroesRepository
import com.example.superheroapp.util.NetworkResponse
import com.example.superheroapp.util.doOnSuccess
import com.example.superheroapp.util.mapSuccess
import javax.inject.Inject

class HeroesRepositoryImpl @Inject constructor(private val remoteDataSource: RemoteDataSource,private val localDataSource: LocalDataSource): HeroesRepository {

    override suspend fun getHeroes(): NetworkResponse<List<Heroes>, Throwable> {
        return remoteDataSource.getHeroes()
            .mapSuccess { heroesDto -> heroesDto.map { hero -> hero.toEntity() } }
            .doOnSuccess { heroesEntity -> localDataSource.insertAll(heroesEntity) }
            .mapSuccess { heroesEntity -> heroesEntity.map { hero -> hero.toDomain() } }
    }
}