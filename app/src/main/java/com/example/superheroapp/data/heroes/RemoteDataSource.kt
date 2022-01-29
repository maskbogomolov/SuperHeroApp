package com.example.superheroapp.data.heroes

import com.example.superheroapp.data.module.HeroesDto
import com.example.superheroapp.util.NetworkResponse
import com.example.superheroapp.util.catchingResponse
import com.example.superheroapp.util.doOnError
import timber.log.Timber
import javax.inject.Inject

interface RemoteDataSource {

    suspend fun getHeroes(): NetworkResponse<List<HeroesDto>, Throwable>
}
class RemoteDataSourceImpl @Inject constructor(private val service: HeroesService): RemoteDataSource{

    override suspend fun getHeroes(): NetworkResponse<List<HeroesDto>,Throwable> {
        return catchingResponse { service.getHeroes() }
            .doOnError { error -> Timber.e("getHeroes from server error", error) }
    }
}