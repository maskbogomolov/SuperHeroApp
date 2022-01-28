package com.example.superheroapp.di

import com.example.superheroapp.data.core.HeroesHttpClient
import com.example.superheroapp.data.core.HeroesHttpClientImpl
import com.example.superheroapp.data.heroes.RemoteDataSource
import com.example.superheroapp.data.heroes.RemoteDataSourceImpl
import dagger.Binds
import dagger.Module
import dagger.Provides

@Module
interface BindsModule {

    @Suppress("FunctionName")
    @Binds
    fun bindHeroesHttpClientImpl_to_HeroesHttpClient(
        impl: HeroesHttpClientImpl
    ): HeroesHttpClient

    @Suppress("FunctionName")
    @Binds
    fun bindRemoteDataSourceImpl_to_RemoteDataSource(
        impl: RemoteDataSourceImpl
    ): RemoteDataSource
}
