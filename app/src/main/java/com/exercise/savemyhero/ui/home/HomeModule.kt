package com.exercise.savemyhero.ui.home

import com.exercise.savemyhero.domain.hero.GetHeroesListUseCase
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
    fun provideHomeViewModelFactory(getHeroesListUseCase: GetHeroesListUseCase): HomeViewModel.Factory {
        return HomeViewModel.Factory(getHeroesListUseCase)
    }
}