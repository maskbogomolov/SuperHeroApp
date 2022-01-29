package com.example.superheroapp.data.core

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.superheroapp.data.heroes.HeroesDao
import com.example.superheroapp.data.heroes.HeroesEntity

@Database(entities = [HeroesEntity::class],version = 1,exportSchema = false)
abstract class AppDatabase: RoomDatabase() {

    abstract fun heroesDao(): HeroesDao

    companion object{

        private var INSTANCE: AppDatabase? = null
        private val LOCK = Any()

        operator fun invoke(context: Context) = INSTANCE ?: synchronized(LOCK){
            INSTANCE ?: createDatabase(context).also { INSTANCE = it }
        }
        private fun createDatabase(context: Context) =
            Room.databaseBuilder(
                context.applicationContext,
                AppDatabase::class.java,
                "app_database"
            )
                .build()
    }
}
