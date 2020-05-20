package com.exercise.savemyhero.ui.hero

import android.util.Log
import androidx.lifecycle.*
import com.exercise.savemyhero.common.ActionResult
import com.exercise.savemyhero.common.Failure
import com.exercise.savemyhero.common.Loading
import com.exercise.savemyhero.common.Success
import com.exercise.savemyhero.domain.hero.Hero
import com.exercise.savemyhero.domain.hero.usecase.SaveHeroInDataBaseUseCase
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

class HeroViewModel @Inject constructor(
    private val saveHeroInDataBaseUseCase: SaveHeroInDataBaseUseCase
) : ViewModel() {

    private val _hero = MutableLiveData<Hero?>()

    val hero: LiveData<Hero?>
        get() = _hero

    private val _favoriteButtonResult = MutableLiveData<Boolean>()

    val favoriteButtonResult: LiveData<Boolean>
        get() = _favoriteButtonResult

    fun displayHero(hero: Hero?) {
        _hero.postValue(hero)
    }

    private fun handleSaveFavoriteHero(result: ActionResult<Boolean>) {
        when (result) {
            is Success -> {
                Log.d("handle saving hero", result.data.toString())
                _favoriteButtonResult.value = true
            }
            is Failure -> {
                Log.d("handle saving hero failing", result.failure.message.orEmpty())
                _favoriteButtonResult.value = false
            }
            is Loading -> {
                _favoriteButtonResult.value = false
            }
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
        private val saveHeroInDataBaseUseCase: SaveHeroInDataBaseUseCase
    ) : ViewModelProvider.Factory {

        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            return modelClass
                .getConstructor(
                    SaveHeroInDataBaseUseCase::class.java
                )
                .newInstance(
                    saveHeroInDataBaseUseCase
                )
        }
    }
}