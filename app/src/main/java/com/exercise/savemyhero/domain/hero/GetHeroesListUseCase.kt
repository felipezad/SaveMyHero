package com.exercise.savemyhero.domain.hero

import android.util.Log
import com.exercise.savemyhero.domain.UseCase
import kotlinx.coroutines.flow.*
import javax.inject.Inject

class GetHeroesListUseCase @Inject constructor(val heroRepository: HeroRepository) : UseCase {

    sealed class Result {
        object Loading : Result()
        data class Success(val productList: List<Hero>) : Result()
        data class Failure(val failure: Throwable) : Result()
    }

    fun execute(numberOfHeroes: Int = 5): Flow<Result> {
        return heroRepository.getHeroes(numberOfHeroes)
            .onStart { Result.Loading }
            .map { it -> Result.Success(it) }
            .catch { error -> Result.Failure(error) }
            .onCompletion {
                Log.d(className, "This userCase finished.")
            }
    }

    override val className: String
        get() = GetHeroesListUseCase::class.simpleName.orEmpty()
}