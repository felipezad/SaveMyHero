package com.exercise.savemyhero.domain

import com.exercise.savemyhero.ui.core.ActionResult
import kotlinx.coroutines.flow.Flow

interface Repository<in T, RESULT : Any> {

    suspend fun saveDataInDataBase(data: T): Flow<ActionResult<RESULT>>
}