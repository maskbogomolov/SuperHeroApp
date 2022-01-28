package com.example.superheroapp.di

import android.app.Application
import android.content.Context

class HeroesApp: Application() {

    lateinit var appComponent: AppComponent
        private set

    override fun onCreate() {
        super.onCreate()
        appComponent = DaggerAppComponent.builder()
            .appDeps(AppDepsImpl())
            .bild()
    }

    private inner class AppDepsImpl : AppDeps {

        override val context: Context = this@HeroesApp
    }
}

val Context.appComponent: AppComponent
    get() = when (this) {
        is HeroesApp -> appComponent
        else -> applicationContext.appComponent
    }