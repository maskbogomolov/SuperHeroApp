package com.example.superheroapp.data.module


import com.google.gson.annotations.SerializedName
import kotlinx.serialization.Serializable

@Serializable
data class HeroesDto(
    @SerializedName("biography")
    val biography: Biography,
    @SerializedName("id")
    val id: Int,
    @SerializedName("images")
    val images: Images,
    @SerializedName("name")
    val name: String,
)