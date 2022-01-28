package com.example.superheroapp.domain

import com.example.superheroapp.data.module.Biography
import com.example.superheroapp.data.module.Images
import com.google.gson.annotations.SerializedName

data class Heroes(
    val id: Int,
    val images: String,
    val name: String,
    val aliases: String,
    val publisher: String
)
