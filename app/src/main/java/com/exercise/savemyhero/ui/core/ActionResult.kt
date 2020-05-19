package com.exercise.savemyhero.ui.core

sealed class ActionResult<out T : Any>
data class Success<out T : Any>(val data: T) : ActionResult<T>()
data class Failure(val failure: Throwable) : ActionResult<Nothing>()
data class Loading(val isLoading: Boolean) : ActionResult<Nothing>()
