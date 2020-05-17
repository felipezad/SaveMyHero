package com.exercise.savemyhero.ui.core

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import javax.inject.Singleton

@Singleton
class SaveMyHeroViewModelFactory() : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T =
        modelClass.getConstructor().newInstance()
}