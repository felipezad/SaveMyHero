package com.exercise.savemyhero.ui.core

import android.app.Application
import com.bumptech.glide.Glide
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
object UiModule {

    @Singleton
    @Provides
    @JvmStatic
    fun provideRequestManagerGlide(application: Application) = Glide.with(application)
}