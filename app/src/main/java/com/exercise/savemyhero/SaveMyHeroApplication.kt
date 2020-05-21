package com.exercise.savemyhero

import com.exercise.savemyhero.common.di.component.DaggerSaveMyHeroApplicationComponent
import dagger.android.AndroidInjector
import dagger.android.support.DaggerApplication

open class SaveMyHeroApplication : DaggerApplication() {

    override fun applicationInjector(): AndroidInjector<out DaggerApplication> {
        return DaggerSaveMyHeroApplicationComponent.factory().create(this)
    }
}