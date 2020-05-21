package com.exercise.savemyhero.common.di.module

import android.app.Application
import com.bumptech.glide.Glide
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
object ApplicationModule {

    @Singleton
    @Provides
    @JvmStatic
    fun provideRequestManagerGlide(application: Application) = Glide.with(application)
}

