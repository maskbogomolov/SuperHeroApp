package com.example.superheroapp.di

import android.content.Context
import com.example.superheroapp.MainActivity
import com.example.superheroapp.presentation.HeroDetailsFragment
import com.example.superheroapp.presentation.HeroesListFragment
import dagger.Component
import dagger.Module

@Component(modules = [AppModule::class,DatabaseModule::class],dependencies = [AppDeps::class])
interface AppComponent {

    fun inject(activity: MainActivity)
    fun inject(heroesListFragment: HeroesListFragment)
    fun inject(heroDetailsFragment: HeroDetailsFragment)

    @Component.Builder
    interface Builder{
        fun appDeps(appDeps: AppDeps): Builder
        fun bild(): AppComponent
    }
}
interface AppDeps{

    val context: Context
}

@Module(includes = [NetworkModule::class,BindsModule::class])
class AppModule