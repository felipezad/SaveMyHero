package com.exercise.savemyhero.ui.hero

import com.exercise.savemyhero.domain.hero.usecase.SaveHeroInDataBaseUseCase
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
    fun provideHeroViewModelFactory(
        saveHeroInDataBaseUseCase: SaveHeroInDataBaseUseCase
    ): HeroViewModel.Factory {
        return HeroViewModel.Factory(
            saveHeroInDataBaseUseCase
        )
    }
}