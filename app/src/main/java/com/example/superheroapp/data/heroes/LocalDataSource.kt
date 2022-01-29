package com.example.superheroapp.data.heroes

import javax.inject.Inject


interface LocalDataSource {
    suspend fun insertAll(entity: List<HeroesEntity>)
}
class LocalDataSourceImpl @Inject constructor(private val dao: HeroesDao): LocalDataSource{

    override suspend fun insertAll(entity: List<HeroesEntity>) = dao.insertAll(entity)
}