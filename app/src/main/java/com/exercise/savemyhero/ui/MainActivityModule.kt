package com.exercise.savemyhero.ui

import com.exercise.savemyhero.ui.favorite.FavoriteHeroModule
import com.exercise.savemyhero.ui.hero.HeroModule
import com.exercise.savemyhero.ui.home.HomeModule
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module(includes = [HomeModule::class, HeroModule::class, FavoriteHeroModule::class])
abstract class MainActivityModule {

    @ContributesAndroidInjector
    abstract fun bind(): MainActivity
}