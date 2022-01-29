package com.example.superheroapp.data.heroes

import kotlinx.coroutines.flow.Flow
import javax.inject.Inject


interface LocalDataSource {
    suspend fun insertAll(entity: List<HeroesEntity>)
    suspend fun getHeroesByFilter(publisher: String): Flow<List<HeroesEntity>>
}
class LocalDataSourceImpl @Inject constructor(private val dao: HeroesDao): LocalDataSource{

    override suspend fun insertAll(entity: List<HeroesEntity>) = dao.insertAll(entity)
    override suspend fun getHeroesByFilter(publisher: String) = dao.getHeroesByFilter(publisher)


}