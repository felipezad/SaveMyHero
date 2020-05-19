package com.exercise.savemyhero.domain

import com.exercise.savemyhero.common.ActionResult
import kotlinx.coroutines.flow.Flow

interface Repository<T> {

    suspend fun insertDataIntoRoom(data: T): Flow<ActionResult<Boolean>>

    suspend fun getElementsFromApi(numberOfElements: Int): Flow<ActionResult<List<T>>>

    suspend fun deleteDataFromRoom(data: T): Flow<ActionResult<Boolean>>

    suspend fun getElementsFromDatabase(): Flow<ActionResult<List<T>>>
}