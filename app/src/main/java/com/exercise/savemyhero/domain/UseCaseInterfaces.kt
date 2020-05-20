package com.exercise.savemyhero.domain

import com.exercise.savemyhero.common.ActionResult
import kotlinx.coroutines.flow.Flow

interface UseCaseConsumerProducer<in T, out K : Any> : UseCase {
    fun execute(param: T): Flow<ActionResult<K>>
}

interface UseCaseProducer<out K : Any> : UseCase {
    fun execute(): Flow<ActionResult<K>>
}

interface UseCase {
    val className: String
}
