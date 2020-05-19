package com.exercise.savemyhero.domain.hero.usecase

import com.exercise.savemyhero.common.ActionResult
import com.exercise.savemyhero.domain.UseCase
import com.exercise.savemyhero.domain.hero.Hero
import com.exercise.savemyhero.domain.hero.HeroRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetFavoritesHeroesListUseCase @Inject constructor(
    private val heroRepository: HeroRepository
) : UseCase<Int, List<Hero>> {

    override fun execute(): Flow<ActionResult<List<Hero>>> {
        return flow {
            val heroes = heroRepository.getElementsFromDatabase()
            heroes.collect { emit(it) }
        }
    }

    override val className: String
        get() = GetFavoritesHeroesListUseCase::class.simpleName.orEmpty()
}