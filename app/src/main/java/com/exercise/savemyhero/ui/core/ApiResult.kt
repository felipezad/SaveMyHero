package com.exercise.savemyhero.ui.core

sealed class ApiResult<out T : Any>
data class Success<out T : Any>(val data: T) : ApiResult<T>()
data class Failure(val failure: Throwable) : ApiResult<Nothing>()
data class Loading(val isLoading: Boolean) : ApiResult<Nothing>()
