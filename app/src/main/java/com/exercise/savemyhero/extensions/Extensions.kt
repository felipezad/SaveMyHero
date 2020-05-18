package com.exercise.savemyhero.extensions

import com.exercise.savemyhero.ui.core.ApiResult
import com.exercise.savemyhero.ui.core.Loading
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.onCompletion
import kotlinx.coroutines.flow.onStart

fun <T : Any> Flow<ApiResult<T>>.prepareLoadingStates() =
    this
        .onStart { emit(Loading(isLoading = true)) }
        .onCompletion { emit(Loading(isLoading = false)) }