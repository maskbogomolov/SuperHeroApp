package com.example.superheroapp.di

import com.example.superheroapp.data.HeroesRepositoryImpl
import com.example.superheroapp.data.core.HeroesHttpClient
import com.example.superheroapp.data.core.HeroesHttpClientImpl
import com.example.superheroapp.data.heroes.LocalDataSource
import com.example.superheroapp.data.heroes.LocalDataSourceImpl
import com.example.superheroapp.data.heroes.RemoteDataSource
import com.example.superheroapp.data.heroes.RemoteDataSourceImpl
import com.example.superheroapp.domain.HeroesRepository
import com.example.superheroapp.util.ThemeUtils
import com.example.superheroapp.util.ThemeUtilsImpl
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

    @Suppress("FunctionName")
    @Binds
    fun bindHeroesRepositoryImpl_to_HeroesRepository(
        impl: HeroesRepositoryImpl
    ):HeroesRepository

    @Suppress("FunctionName")
    @Binds
    fun bindLocalDataSourceImpl_to_LocalDataSource(
        impl: LocalDataSourceImpl
    ): LocalDataSource

    @Suppress("FunctionName")
    @Binds
    fun bindThemeUtilsImpl_to_ThemeUtils(
        impl: ThemeUtilsImpl
    ): ThemeUtils
}
