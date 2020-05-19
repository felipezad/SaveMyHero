package com.exercise.savemyhero.ui.home

import com.exercise.savemyhero.domain.hero.usecase.GetHeroesListUseCase
import com.exercise.savemyhero.domain.hero.usecase.SaveHeroInDataBaseUseCase
import dagger.Module
import dagger.Provides
import dagger.android.ContributesAndroidInjector
import javax.inject.Singleton


@Module(includes = [HomeViewModelFactory::class])
abstract class HomeModule {

    @ContributesAndroidInjector
    abstract fun bind(): HomeFragment
}

@Module
object HomeViewModelFactory {

    @Singleton
    @Provides
    @JvmStatic
    fun provideHomeViewModelFactory(
        getHeroesListUseCase: GetHeroesListUseCase,
        saveHeroInDataBaseUseCase: SaveHeroInDataBaseUseCase
    ): HomeViewModel.Factory {
        return HomeViewModel.Factory(getHeroesListUseCase, saveHeroInDataBaseUseCase)
    }
}