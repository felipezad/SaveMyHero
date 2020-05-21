package com.exercise.savemyhero.common.di.module

import android.app.Application
import com.exercise.savemyhero.data.local.SaveMyHeroDatabase
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
object SaveMyHeroDatabaseModule {

    @Singleton
    @JvmStatic
    @Provides
    fun provideDatabase(application: Application) =
        SaveMyHeroDatabase.getInstance(
            application
        )

    @Singleton
    @JvmStatic
    @Provides
    fun provideHeroDao(database: SaveMyHeroDatabase) = database.heroDao()
}