package com.example.superheroapp.di

import com.example.superheroapp.data.core.HeroesHttpClient
import com.example.superheroapp.data.core.HeroesHttpClientImpl
import dagger.Binds
import dagger.Module
import dagger.Provides

@Module
interface BindsModule {

    @Suppress("FunctionName")
    @Binds
    fun bHeroesHttpClientImpl_to_HeroesHttpClient(
        impl: HeroesHttpClientImpl
    ): HeroesHttpClient
}
