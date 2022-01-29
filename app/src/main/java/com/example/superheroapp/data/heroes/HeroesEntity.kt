package com.example.superheroapp.data.heroes

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.superheroapp.util.Const.HEROES_TABLE

@Entity(tableName = HEROES_TABLE)
data class HeroesEntity(
    @PrimaryKey
    val id: Int,
    val images: String,
    val name: String,
    val aliases: String,
    val publisher: String
)
