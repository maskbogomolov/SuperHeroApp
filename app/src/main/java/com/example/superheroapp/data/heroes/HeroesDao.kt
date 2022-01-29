package com.example.superheroapp.data.heroes

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface HeroesDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(entity: List<HeroesEntity>)

    @Query("""SELECT * FROM HEROES_TABLE WHERE publisher LIKE '%' || :publisher || '%'""")
    fun getHeroesByFilter(publisher: String): Flow<List<HeroesEntity>>
}