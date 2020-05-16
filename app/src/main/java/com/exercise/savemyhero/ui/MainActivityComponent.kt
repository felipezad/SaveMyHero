package com.exercise.savemyhero.ui

import com.exercise.savemyhero.ActivityScope
import dagger.Subcomponent
import dagger.android.AndroidInjector

@ActivityScope
@Subcomponent
interface MainActivityComponent : AndroidInjector<MainActivity> {

    @Subcomponent.Factory
    interface Factory : AndroidInjector.Factory<MainActivity> {}
}