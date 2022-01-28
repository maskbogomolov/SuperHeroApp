package com.example.superheroapp.data.core

import com.example.superheroapp.data.HeroesService
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import javax.inject.Inject

interface HeroesHttpClient {
    val api: HeroesService
}
class HeroesHttpClientImpl @Inject constructor(): HeroesHttpClient{

    val logger = HttpLoggingInterceptor().setLevel(level = HttpLoggingInterceptor.Level.BODY)
    val client = OkHttpClient.Builder()
        .addInterceptor(logger)
        .build()

    val json = Json {
        ignoreUnknownKeys = true
    }

    val retrofit = Retrofit.Builder()
        .baseUrl("https://akabab.github.io/superhero-api/api/")
        .addConverterFactory(json.asConverterFactory("application/json".toMediaTypeOrNull()!!))
        .client(client)
        .build()

    override val api: HeroesService by lazy { retrofit.create(HeroesService::class.java) }

}