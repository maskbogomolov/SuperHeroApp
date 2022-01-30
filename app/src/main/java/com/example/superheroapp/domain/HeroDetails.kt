package com.example.superheroapp.domain

import com.google.gson.annotations.SerializedName

data class HeroDetails(
    val aliases: String,
    val alignment: String,
    val alterEgos: String,
    val firstAppearance: String,
    val fullName: String,
    val placeOfBirth: String,
    val publisher: String
)
