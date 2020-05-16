package com.exercise.savemyhero.ui.home

import dagger.Module
import dagger.Provides
import dagger.android.ContributesAndroidInjector
import javax.inject.Provider

@Module
abstract class HomeModule {

    @ContributesAndroidInjector(modules = [ProvideViewModel::class])
    abstract fun bind(): HomeFragment

    @Module
    class ProvideViewModel {

        @Provides
        fun provideHomeViewModel() = HomeViewModel()

        @Provides
        fun provideHomeViewModelFactory(provider: Provider<HomeViewModel>) =
            HomeViewModel.Factory(provider)
    }
}