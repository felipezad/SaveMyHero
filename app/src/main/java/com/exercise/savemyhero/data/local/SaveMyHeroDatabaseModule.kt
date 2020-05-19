package com.exercise.savemyhero.data.local

import android.app.Application
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
object SaveMyHeroDatabaseModule {

    @Singleton
    @JvmStatic
    @Provides
    fun provideDatabase(application: Application) =
        SaveMyHeroDatabase.getInstance(application)

    @Singleton
    @JvmStatic
    @Provides
    fun provideHeroDao(database: SaveMyHeroDatabase) = database.heroDao()
}