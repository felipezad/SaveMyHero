package com.exercise.savemyhero.domain

import com.exercise.savemyhero.domain.hero.Hero
import com.exercise.savemyhero.common.ActionResult
import kotlinx.coroutines.flow.Flow

interface Repository<in T> {

    suspend fun saveFavoriteHero(data: T): Flow<ActionResult<Boolean>>

    suspend fun getHeroesFromApi(numberOfHeroes: Int): Flow<ActionResult<List<Hero>>>

    suspend fun deleteFavoriteHero(data: T): Flow<ActionResult<Boolean>>
}