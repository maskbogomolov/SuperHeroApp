package com.example.superheroapp.data.heroes

import com.google.gson.annotations.SerializedName
import kotlinx.serialization.Serializable

@Serializable
data class HeroDetailsDto(
    @SerializedName("id")
    val id: Int,
    @SerializedName("name")
    val name: String,
    @SerializedName("alter-egos")
    val alterEgo: String,
    @SerializedName("aliases")
    val aliases: List<String>,
    @SerializedName("publisher")
    val publisher: String,
    @SerializedName("first-appearance")
    val firstAppearance: String,
    @SerializedName("place-of-birth")
    val placeOfBirth: String
)
