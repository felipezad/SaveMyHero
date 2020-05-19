package com.exercise.savemyhero.ui.home

import android.util.Log
import androidx.lifecycle.*
import com.exercise.savemyhero.domain.hero.Hero
import com.exercise.savemyhero.domain.hero.usecase.GetHeroesListUseCase
import com.exercise.savemyhero.domain.hero.usecase.SaveHeroInDataBaseUseCase
import com.exercise.savemyhero.ui.core.ActionResult
import com.exercise.savemyhero.ui.core.Failure
import com.exercise.savemyhero.ui.core.Loading
import com.exercise.savemyhero.ui.core.Success
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

class HomeViewModel @Inject constructor(
    private val getHeroesListUseCase: GetHeroesListUseCase,
    private val saveHeroInDataBaseUseCase: SaveHeroInDataBaseUseCase
) : ViewModel() {

    private val _heroList = MutableLiveData<List<Hero>>()

    val heroList: LiveData<List<Hero>>
        get() = _heroList

    private val _text = MutableLiveData<String>().apply {
        value = "This is Home Hero Fragment"
    }
    val text: LiveData<String> = _text

    private fun handleListOfHeroes(result: ActionResult<List<Hero>>) {
        when (result) {
            is Success -> {
                _text.value = "Success ${result.data.size}"
                _heroList.postValue(result.data)
            }
            is Failure -> {
                _text.value = "Failure ${result.failure}"
            }
            is Loading -> {
                if (result.isLoading)
                    _text.value = "Loading ...."
            }
        }
    }

    private fun handleSaveFavoriteHero(result: ActionResult<Boolean>) {
        when (result) {
            is Success -> {
                Log.d("saving hero", result.data.toString())
            }
            is Failure -> {
                Log.d("failure hero", result.failure.message.orEmpty())
            }
            is Loading -> {
                Log.d("loading saving hero", result.isLoading.toString())
            }
        }
    }

    fun getListOfHeroes() {
        viewModelScope.launch {
            getHeroesListUseCase
                .execute(5)
                .collect { it -> handleListOfHeroes(it) }
        }
    }

    fun saveFavoriteHero(hero: Hero) {
        viewModelScope.launch {
            saveHeroInDataBaseUseCase
                .execute(hero)
                .collect { it ->
                    handleSaveFavoriteHero(it)
                }
        }
    }


    class Factory @Inject constructor(
        private val getHeroesListUseCase: GetHeroesListUseCase,
        private val saveHeroInDataBaseUseCase: SaveHeroInDataBaseUseCase
    ) : ViewModelProvider.Factory {

        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            return modelClass
                .getConstructor(
                    GetHeroesListUseCase::class.java,
                    SaveHeroInDataBaseUseCase::class.java
                )
                .newInstance(getHeroesListUseCase, saveHeroInDataBaseUseCase)
        }
    }
}