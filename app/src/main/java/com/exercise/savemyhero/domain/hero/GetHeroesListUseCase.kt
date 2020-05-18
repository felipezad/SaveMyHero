package com.exercise.savemyhero.domain.hero

import com.exercise.savemyhero.domain.UseCase
import com.exercise.savemyhero.ui.core.ApiResult
import com.exercise.savemyhero.ui.core.Success
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class GetHeroesListUseCase @Inject constructor(
    private val heroRepository: HeroRepository
) : UseCase {

    fun execute(numberOfHeroes: Int = 5): Flow<ApiResult<List<Hero>>> {
        return flow {
            heroRepository.getHeroes(numberOfHeroes)
                .map { it -> emit(Success(it)) }
        }
    }

    override val className: String
        get() = GetHeroesListUseCase::class.simpleName.orEmpty()
}