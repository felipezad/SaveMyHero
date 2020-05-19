package com.exercise.savemyhero.domain

import com.exercise.savemyhero.common.ActionResult
import kotlinx.coroutines.flow.Flow

interface UseCase<in T, out K : Any> {

    val className: String

    fun execute(param: T): Flow<ActionResult<K>>
}