package com.exercise.savemyhero.data

import com.exercise.savemyhero.data.remote.Hero
import retrofit2.http.GET
import retrofit2.http.Query

interface MarvelService {

    @GET("request-characters")
    suspend fun requestHeroes(@Query("boo") name: String): List<Hero>
}