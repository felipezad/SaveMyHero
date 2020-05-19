package com.exercise.savemyhero.ui.favorite

import android.util.Log
import androidx.lifecycle.*
import com.exercise.savemyhero.common.ActionResult
import com.exercise.savemyhero.common.Failure
import com.exercise.savemyhero.common.Loading
import com.exercise.savemyhero.common.Success
import com.exercise.savemyhero.domain.hero.Hero
import com.exercise.savemyhero.domain.hero.usecase.DeleteHeroInDataBaseUseCase
import com.exercise.savemyhero.domain.hero.usecase.GetFavoritesHeroesListUseCase
import com.exercise.savemyhero.domain.hero.usecase.SaveHeroInDataBaseUseCase
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

class FavoriteViewModel @Inject constructor(
    private val getFavoritesHeroesListUseCase: GetFavoritesHeroesListUseCase,
    private val saveHeroInDataBaseUseCase: SaveHeroInDataBaseUseCase,
    private val deleteHeroInDataBaseUseCase: DeleteHeroInDataBaseUseCase
) : ViewModel() {

    private val _heroList = MutableLiveData<List<Hero>>()

    val heroList: LiveData<List<Hero>>
        get() = _heroList

    private fun handleListOfHeroes(result: ActionResult<List<Hero>>) {
        when (result) {
            is Success -> {
                Log.d("favorites", "Success ${result.data.size}")
                _heroList.postValue(result.data)
            }
            is Failure -> {
                Log.d("favorites", "Failure ${result.failure}")
            }
            is Loading -> {
                Log.d("favorites", "Loading ${result.isLoading}")
            }
        }
    }

    private fun handleSaveFavoriteHero(result: ActionResult<Boolean>) {
        when (result) {
            is Success -> {
                Log.d("handle saving hero", result.data.toString())
            }
            is Failure -> {
                Log.d("handle saving hero failing", result.failure.message.orEmpty())
            }
            is Loading -> {
                Log.d("handle saving hero loading", result.isLoading.toString())
            }
        }
    }


    private fun handleDeleteFavoriteHero(result: ActionResult<Boolean>) {
        when (result) {
            is Success -> {
                Log.d("handle delete hero", result.data.toString())
            }
            is Failure -> {
                Log.d("handle delete hero failure", result.failure.message.orEmpty())
            }
            is Loading -> {
                Log.d("handle delete hero loading", result.isLoading.toString())
            }
        }
    }

    fun getListOfHeroes() {
        viewModelScope.launch {
            getFavoritesHeroesListUseCase
                .execute()
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

    fun deleteFavoriteHero(hero: Hero) {
        viewModelScope.launch {
            deleteHeroInDataBaseUseCase
                .execute(hero)
                .collect {
                    handleDeleteFavoriteHero(it)
                }
        }
    }

    class Factory @Inject constructor(
        private val getFavoritesHeroesListUseCase: GetFavoritesHeroesListUseCase,
        private val saveHeroInDataBaseUseCase: SaveHeroInDataBaseUseCase,
        private val deleteHeroInDataBaseUseCase: DeleteHeroInDataBaseUseCase
    ) : ViewModelProvider.Factory {

        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            return modelClass
                .getConstructor(
                    GetFavoritesHeroesListUseCase::class.java,
                    SaveHeroInDataBaseUseCase::class.java,
                    DeleteHeroInDataBaseUseCase::class.java
                )
                .newInstance(
                    getFavoritesHeroesListUseCase,
                    saveHeroInDataBaseUseCase,
                    deleteHeroInDataBaseUseCase
                )
        }
    }
}