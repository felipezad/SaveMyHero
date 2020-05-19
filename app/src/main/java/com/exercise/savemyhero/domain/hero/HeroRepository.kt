package com.exercise.savemyhero.domain.hero

import com.exercise.savemyhero.common.ActionResult
import com.exercise.savemyhero.common.Failure
import com.exercise.savemyhero.common.Success
import com.exercise.savemyhero.common.prepareLoadingStates
import com.exercise.savemyhero.data.local.HeroDao
import com.exercise.savemyhero.data.remote.MarvelService
import com.exercise.savemyhero.domain.Repository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import java.io.IOException
import javax.inject.Inject

class HeroRepository @Inject constructor(
    private val heroMapper: HeroMapper,
    private val marvelService: MarvelService,
    private val heroDao: HeroDao
) : Repository<Hero> {

    override suspend fun getElementsFromDatabase(): Flow<ActionResult<List<Hero>>> {
        return flow {
            try {
                val listHeroes = heroDao.getFavoriteHeroes().first()
                emit(Success(listHeroes))
            } catch (error: IOException) {
                emit(Failure(error))
            }
        }
            .prepareLoadingStates()
            .flowOn(Dispatchers.IO)
    }

    override suspend fun getElementsFromApi(numberOfElements: Int): Flow<ActionResult<List<Hero>>> {
        return flow {
            try {
                val latestHeroes = marvelService.requestHeroes(limit = numberOfElements)
                val value = heroMapper.to(from = latestHeroes.data.results)
                emit(Success(value))
            } catch (error: IOException) {
                emit(Failure(error))
            }
        }
            .prepareLoadingStates()
            .flowOn(Dispatchers.IO)
    }

    override suspend fun insertDataIntoRoom(data: Hero): Flow<ActionResult<Boolean>> {
        return flow {
            try {
                heroDao.insert(data)
                emit(Success(true))
            } catch (e: Exception) {
                emit(Failure(e))
            }
        }
            .prepareLoadingStates()
            .flowOn(Dispatchers.IO)
    }

    override suspend fun deleteDataFromRoom(data: Hero): Flow<ActionResult<Boolean>> {
        return flow {
            try {
                heroDao.delete(data)
                emit(Success(true))
            } catch (e: Exception) {
                emit(Failure(e))
            }
        }
            .prepareLoadingStates()
            .flowOn(Dispatchers.IO)
    }
}