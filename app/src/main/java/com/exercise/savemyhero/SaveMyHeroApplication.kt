package com.exercise.savemyhero

import dagger.android.AndroidInjector
import dagger.android.support.DaggerApplication

class SaveMyHeroApplication : DaggerApplication() {

    override fun applicationInjector(): AndroidInjector<out DaggerApplication> {
        return DaggerSaveMyHeroApplicationComponent.factory().create(this)
    }
}