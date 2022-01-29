package com.example.superheroapp.di

import com.example.superheroapp.data.heroes.HeroesService
import com.example.superheroapp.data.core.HeroesHttpClient
import dagger.Module
import dagger.Provides

@Module
class NetworkModule {

    @Provides
    fun provideHeroesService(client: HeroesHttpClient): HeroesService {
        return client.api
    }
}