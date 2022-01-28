package com.example.superheroapp.data.module


import com.google.gson.annotations.SerializedName
import kotlinx.serialization.Serializable

@Serializable
data class Biography(
    @SerializedName("aliases")
    val aliases: List<String?>,
    @SerializedName("publisher")
    val publisher: String?
)