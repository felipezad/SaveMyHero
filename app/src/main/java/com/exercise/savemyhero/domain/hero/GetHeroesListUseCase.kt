package com.exercise.savemyhero.domain.hero

import com.exercise.savemyhero.domain.UseCase
import com.exercise.savemyhero.ui.core.ApiResult
import com.exercise.savemyhero.ui.core.Failure
import com.exercise.savemyhero.ui.core.Loading
import com.exercise.savemyhero.ui.core.Success
import kotlinx.coroutines.flow.*
import javax.inject.Inject

class GetHeroesListUseCase @Inject constructor(
    private val heroRepository: HeroRepository
) : UseCase {

    fun execute(numberOfHeroes: Int = 5): Flow<ApiResult<List<Hero>>> {
        return flow {
            heroRepository.getHeroes(numberOfHeroes)
                .onStart { emit(Loading(isLoading = true)) }
                .map { it -> emit(Success(it)) }
                .catch { error -> emit(Failure(error)) }
                .onCompletion {
                    emit(Loading(isLoading = false))
                }
        }
    }

    override val className: String
        get() = GetHeroesListUseCase::class.simpleName.orEmpty()
}