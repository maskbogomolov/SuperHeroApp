package com.example.superheroapp.data.module


import com.google.gson.annotations.SerializedName
import kotlinx.serialization.Serializable

@Serializable
data class HeroDetailsDto(
    @SerializedName("aliases")
    val aliases: List<String>,
    @SerializedName("alignment")
    val alignment: String,
    @SerializedName("alterEgos")
    val alterEgos: String,
    @SerializedName("firstAppearance")
    val firstAppearance: String,
    @SerializedName("fullName")
    val fullName: String,
    @SerializedName("placeOfBirth")
    val placeOfBirth: String,
    @SerializedName("publisher")
    val publisher: String
)