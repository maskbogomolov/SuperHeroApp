package com.example.superheroapp.data.module


import com.google.gson.annotations.SerializedName
import kotlinx.serialization.Serializable

@Serializable
data class Images(
    @SerializedName("md")
    val md: String
)