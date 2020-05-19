package com.exercise.savemyhero.ui.hero

import dagger.Module
import dagger.Provides
import dagger.android.ContributesAndroidInjector
import javax.inject.Singleton

@Module(includes = [HeroViewModelFactory::class])
abstract class HeroModule {

    @ContributesAndroidInjector
    abstract fun bind(): HeroFragment
}

@Module
object HeroViewModelFactory {

    @Singleton
    @Provides
    @JvmStatic
    fun provideHeroViewModelFactory(): HeroViewModel.Factory {
        return HeroViewModel.Factory()
    }
}