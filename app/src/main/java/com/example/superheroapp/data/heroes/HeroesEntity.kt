package com.example.superheroapp.data.heroes

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "heroes_table")
data class HeroesEntity(
    @PrimaryKey
    val id: Int,
    val images: String,
    val name: String,
    val aliases: String,
    val publisher: String
)
