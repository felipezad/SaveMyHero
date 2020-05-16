package com.exercise.savemyhero.ui

import android.content.Context
import com.exercise.savemyhero.data.remote.NetworkModule
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjectionModule
import dagger.android.AndroidInjector
import javax.inject.Singleton

@Singleton
@Component(modules = [AndroidInjectionModule::class, NetworkModule::class])
interface SaveMyHeroApplicationComponent : AndroidInjector<SaveMyHeroApplication> {

    @Component.Factory
    interface Factory {
        fun create(@BindsInstance context: Context): SaveMyHeroApplicationComponent
    }
}