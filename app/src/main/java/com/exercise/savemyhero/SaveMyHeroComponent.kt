package com.exercise.savemyhero

import android.content.Context
import com.exercise.savemyhero.ui.MainActivityModule
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjectionModule
import dagger.android.AndroidInjector
import javax.inject.Singleton

@Singleton
@Component(modules = [AndroidInjectionModule::class, MainActivityModule::class])
interface SaveMyHeroApplicationComponent : AndroidInjector<SaveMyHeroApplication> {

    @Component.Factory
    interface Factory {
        fun create(@BindsInstance context: Context): SaveMyHeroApplicationComponent
    }
}