package com.exercise.savemyhero.ui.home

import androidx.lifecycle.ViewModelProvider
import com.exercise.savemyhero.ViewModelKey
import dagger.Module
import dagger.Provides
import dagger.android.ContributesAndroidInjector
import dagger.multibindings.IntoMap


@Module(includes = [HomeModule.ProvideViewModel::class])
abstract class HomeModule {

    @ContributesAndroidInjector(modules = [InjectViewModel::class])
    abstract fun bind(): HomeFragment

    @Module
    class ProvideViewModel {

        @Provides
        @IntoMap
        @ViewModelKey(HomeViewModel::class)
        fun provideHomeViewModel() = HomeViewModel()
    }

    @Module
    class InjectViewModel {

        @Provides
        fun provideHomeViewModel(
            factory: ViewModelProvider.Factory,
            target: HomeFragment
        ) = ViewModelProvider(target, factory).get(HomeViewModel::class.java)
    }
}