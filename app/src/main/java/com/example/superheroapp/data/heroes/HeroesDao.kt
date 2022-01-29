package com.example.superheroapp.data.heroes

import androidx.room.Dao
import androidx.room.Insert

@Dao
interface HeroesDao {

    @Insert
    suspend fun insertAll(entity: List<HeroesEntity>)
}