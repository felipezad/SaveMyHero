package com.exercise.savemyhero.ui.home

import androidx.lifecycle.*
import com.exercise.savemyhero.domain.hero.GetHeroesListUseCase
import com.exercise.savemyhero.domain.hero.Hero
import com.exercise.savemyhero.ui.core.ApiResult
import com.exercise.savemyhero.ui.core.Failure
import com.exercise.savemyhero.ui.core.Loading
import com.exercise.savemyhero.ui.core.Success
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

class HomeViewModel @Inject constructor(
    private val getHeroesListUseCase: GetHeroesListUseCase
) : ViewModel() {

    private val _heroList = MutableLiveData<List<Hero>>()

    val heroList: LiveData<List<Hero>>
        get() = _heroList

    private val _text = MutableLiveData<String>().apply {
        value = "This is Home Hero Fragment"
    }
    val text: LiveData<String> = _text

    private fun handleListOfHeroes(result: ApiResult<List<Hero>>) {
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

    fun getListOfHeroes() {
        viewModelScope.launch {
            getHeroesListUseCase
                .execute()
                .collect { it -> handleListOfHeroes(it) }
        }
    }

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