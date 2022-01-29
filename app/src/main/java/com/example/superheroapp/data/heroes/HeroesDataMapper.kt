package com.example.superheroapp.data.heroes

import com.example.superheroapp.data.module.HeroesDto
import com.example.superheroapp.domain.Heroes

fun HeroesDto.toEntity(): HeroesEntity {
    return HeroesEntity(
        id = this.id,
        images = this.images.md,
        name = this.name,
        aliases = this.biography.aliases[0] ?: "not found",
        publisher = this.biography.publisher ?: "not found"

    )
}