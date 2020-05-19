package com.exercise.savemyhero.ui.hero

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.exercise.savemyhero.domain.hero.Hero
import javax.inject.Inject

class HeroViewModel : ViewModel() {

    private val _hero = MutableLiveData<Hero>()
    val hero: LiveData<Hero>
        get() = _hero

    fun displayHero(hero: Hero) {
        _hero.postValue(hero)
    }

    class Factory @Inject constructor() : ViewModelProvider.Factory {

        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            return modelClass.newInstance()
        }
    }
}