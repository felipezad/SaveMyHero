package com.exercise.savemyhero.data

import com.exercise.savemyhero.data.remote.model.HeroResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface MarvelService {

    @GET("characters")
    suspend fun requestHeroes(@Query("limit") limit: Int = 5): List<HeroResponse>
}