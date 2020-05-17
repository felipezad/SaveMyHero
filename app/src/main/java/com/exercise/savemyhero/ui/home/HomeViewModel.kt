package com.exercise.savemyhero.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.exercise.savemyhero.domain.hero.GetHeroesListUseCase
import javax.inject.Inject

class HomeViewModel @Inject constructor(
    private val getHeroesListUseCase: GetHeroesListUseCase
) : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "This is home Fragment"
    }
    val text: LiveData<String> = _text

    class Factory @Inject constructor(
        private val getHeroesListUseCase: GetHeroesListUseCase
    ) : ViewModelProvider.Factory {

        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            return modelClass
                .getConstructor(GetHeroesListUseCase::class.java)
                .newInstance(getHeroesListUseCase)
        }
    }
}