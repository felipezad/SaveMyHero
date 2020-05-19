package com.exercise.savemyhero

import android.app.Application
import com.exercise.savemyhero.data.local.SaveMyHeroDatabaseModule
import com.exercise.savemyhero.data.remote.NetworkModule
import com.exercise.savemyhero.ui.MainActivityModule
import com.exercise.savemyhero.ui.core.UiModule
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjectionModule
import dagger.android.AndroidInjector
import javax.inject.Singleton

@Singleton
@Component(modules = [
    AndroidInjectionModule::class,
    MainActivityModule::class,
    NetworkModule::class,
    UiModule::class,
    SaveMyHeroDatabaseModule::class
])
interface SaveMyHeroApplicationComponent : AndroidInjector<SaveMyHeroApplication> {

    @Component.Factory
    interface Factory {
        fun create(
            @BindsInstance application: Application
        ): SaveMyHeroApplicationComponent
    }
}