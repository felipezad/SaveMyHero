package com.exercise.savemyhero.common.di.ui

import com.exercise.savemyhero.ui.MainActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module(includes = [FragmentBuilderModule::class])
abstract class MainActivityModule {

    @ContributesAndroidInjector
    abstract fun bind(): MainActivity
}