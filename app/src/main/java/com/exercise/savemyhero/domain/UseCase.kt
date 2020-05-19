package com.exercise.savemyhero.domain

import com.exercise.savemyhero.common.ActionResult
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

interface UseCase<in T, out K : Any> {

    val className: String

    fun execute(param: T): Flow<ActionResult<K>> {
        return flow { print("Execute method not implemented on concrete class") }
    }

    fun execute(): Flow<ActionResult<K>> {
        return flow { print("Execute method not implemented on concrete class") }
    }
}