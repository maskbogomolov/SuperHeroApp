package com.example.superheroapp.data

import com.example.superheroapp.data.heroes.HeroesEntity
import com.example.superheroapp.data.module.HeroesDto
import com.example.superheroapp.domain.Heroes

fun HeroesEntity.toDomain(): Heroes{
    return Heroes(
        id = this.id,
        images = this.images,
        name = this.name,
        aliases = this.aliases,
        publisher = this.publisher
    )
}
