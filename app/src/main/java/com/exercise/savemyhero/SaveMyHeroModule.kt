package com.exercise.savemyhero

import com.exercise.savemyhero.data.remote.NetworkModule
import com.exercise.savemyhero.ui.core.SaveMyHeroViewModelFactory
import dagger.Module
import dagger.Provides

@Module(includes = [NetworkModule::class])
class SaveMyHeroModule {

    @Provides
    fun provideHomeViewModelFactory() = SaveMyHeroViewModelFactory()
}