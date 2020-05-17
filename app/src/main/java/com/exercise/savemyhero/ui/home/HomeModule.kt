package com.exercise.savemyhero.ui.home

import dagger.Module
import dagger.android.ContributesAndroidInjector


@Module
abstract class HomeModule {

    @ContributesAndroidInjector
    abstract fun bind(): HomeFragment
}