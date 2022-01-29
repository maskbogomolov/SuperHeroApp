package com.example.superheroapp.di

import android.content.Context
import com.example.superheroapp.data.core.AppDatabase
import dagger.Module
import dagger.Provides

@Module
class DatabaseModule {

    @Provides
    fun provideAppDatabase(context: Context) = AppDatabase.invoke(context)

    @Provides
    fun provideDao(database: AppDatabase) = database.heroesDao()
}