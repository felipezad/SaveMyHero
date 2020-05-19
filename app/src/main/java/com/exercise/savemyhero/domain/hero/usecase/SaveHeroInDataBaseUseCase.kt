package com.exercise.savemyhero.domain.hero.usecase

import com.exercise.savemyhero.domain.UseCase
import com.exercise.savemyhero.domain.hero.Hero
import com.exercise.savemyhero.domain.hero.HeroRepository
import com.exercise.savemyhero.common.ActionResult
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class SaveHeroInDataBaseUseCase @Inject constructor(
    private val heroRepository: HeroRepository
) : UseCase<Hero, Boolean> {
    override val className: String
        get() = SaveHeroInDataBaseUseCase::class.simpleName.orEmpty()

    override fun execute(param: Hero): Flow<ActionResult<Boolean>> {
        return flow {
            heroRepository.insertDataIntoRoom(param).collect { it ->
                emit(it)
            }
        }
    }
}