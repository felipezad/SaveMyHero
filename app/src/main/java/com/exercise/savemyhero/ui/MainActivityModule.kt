package com.exercise.savemyhero.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.exercise.savemyhero.data.remote.NetworkModule
import com.exercise.savemyhero.ui.core.SaveMyHeroViewModelFactory
import com.exercise.savemyhero.ui.home.HomeModule
import dagger.Module
import dagger.Provides
import javax.inject.Provider

@Module(includes = [NetworkModule::class, HomeModule::class])
class MainActivityModule {

    @Provides
    fun provideViewModelFactoryProviders(
        providers: Map<Class<out ViewModel>, @JvmSuppressWildcards Provider<ViewModel>>
    ): ViewModelProvider.Factory = SaveMyHeroViewModelFactory(providers)
}