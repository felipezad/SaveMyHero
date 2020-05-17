package com.exercise.savemyhero.domain.hero

import com.exercise.savemyhero.data.remote.MarvelService
import com.exercise.savemyhero.domain.Repository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class HeroRepository @Inject constructor(
    private val heroMapper: HeroMapper,
    private val marvelService: MarvelService
) : Repository {

    fun getHeroes(numberOfHeroes: Int): Flow<List<Hero>> {
        return flow {
            val latestHeroes = marvelService.requestHeroes(limit = numberOfHeroes)
            emit(heroMapper.to(latestHeroes))
        }.flowOn(Dispatchers.IO)
    }
}