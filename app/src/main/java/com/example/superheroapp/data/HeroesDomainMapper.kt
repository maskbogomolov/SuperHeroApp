package com.example.superheroapp.data

import com.example.superheroapp.data.heroes.HeroesEntity
import com.example.superheroapp.data.module.HeroDetailsDto
import com.example.superheroapp.data.module.HeroesDto
import com.example.superheroapp.domain.HeroDetails
import com.example.superheroapp.domain.Heroes
import com.google.gson.annotations.SerializedName

fun HeroesEntity.toDomain(): Heroes{
    return Heroes(
        id = this.id,
        images = this.images,
        name = this.name,
        aliases = this.aliases,
        publisher = this.publisher
    )
}
fun HeroDetailsDto.toHeroDetails(): HeroDetails{
    return HeroDetails(
        aliases = this.aliases[0],
        alignment = alignment,
        alterEgos = alterEgos,
        fullName = fullName,
        placeOfBirth = placeOfBirth,
        publisher = publisher,
        firstAppearance = firstAppearance
    )
}
