package com.example.superheroapp.di

import com.example.superheroapp.data.HeroesService
import com.example.superheroapp.data.core.HeroesHttpClient
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class NetworkModule {

    @Provides
    fun provideHeroesService(client: HeroesHttpClient): HeroesService{
        return client.api
    }
}