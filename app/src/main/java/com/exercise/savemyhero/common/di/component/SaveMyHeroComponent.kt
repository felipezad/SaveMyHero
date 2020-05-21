package com.exercise.savemyhero.common.di.component

import android.app.Application
import com.exercise.savemyhero.SaveMyHeroApplication
import com.exercise.savemyhero.common.di.module.ApplicationModule
import com.exercise.savemyhero.common.di.module.NetworkModule
import com.exercise.savemyhero.common.di.ui.FragmentBuilderModule
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjectionModule
import dagger.android.AndroidInjector
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        ApplicationModule::class,
        AndroidInjectionModule::class,
        FragmentBuilderModule::class,
        NetworkModule::class
    ]
)
interface SaveMyHeroApplicationComponent : AndroidInjector<SaveMyHeroApplication> {

    @Component.Factory
    interface Factory {
        fun create(@BindsInstance application: Application): SaveMyHeroApplicationComponent
    }
}