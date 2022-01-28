package com.example.superheroapp.data.module


import com.google.gson.annotations.SerializedName
import kotlinx.serialization.Serializable

@Serializable
data class Images(
    @SerializedName("lg")
    val lg: String,
    @SerializedName("md")
    val md: String,
    @SerializedName("sm")
    val sm: String,
    @SerializedName("xs")
    val xs: String
)