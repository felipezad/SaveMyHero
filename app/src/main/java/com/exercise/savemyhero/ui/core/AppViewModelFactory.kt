package com.exercise.savemyhero.ui.core

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import javax.inject.Provider

class SaveMyHeroViewModelFactory(
    private val providers: Map<Class<out ViewModel>, Provider<ViewModel>>
) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T =
        requireNotNull(getProvider(modelClass).get()) {
            "Provider for $modelClass returned null"
        }


    private fun <T : ViewModel> getProvider(modelClass: Class<T>): Provider<T> =
        try {
            requireNotNull(providers[modelClass] as Provider<T>) {
                "No ViewModel provider is bound for class $modelClass"
            }
        } catch (error: ClassCastException) {
            error("Wrong provider type registered for ViewModel type $error")
        }
}