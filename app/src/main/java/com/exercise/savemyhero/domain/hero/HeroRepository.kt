package com.exercise.savemyhero.domain.hero

import com.exercise.savemyhero.data.remote.MarvelService
import com.exercise.savemyhero.domain.Repository
import com.exercise.savemyhero.ui.core.ApiResult
import com.exercise.savemyhero.ui.core.Success
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class HeroRepository @Inject constructor(
    private val heroMapper: HeroMapper,
    private val marvelService: MarvelService
) : Repository {

    fun getHeroes(numberOfHeroes: Int): Flow<ApiResult<List<Hero>>> {
        return flow {
            val latestHeroes = marvelService.requestHeroes(limit = numberOfHeroes)
            val value = heroMapper.to(from = latestHeroes.data.results)
            emit(Success(value))
        }
    }
}