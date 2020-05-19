package com.exercise.savemyhero.domain.hero

import com.exercise.savemyhero.data.remote.model.HeroResponse
import com.exercise.savemyhero.domain.Mapper
import javax.inject.Inject

class HeroMapper @Inject constructor() : Mapper<HeroResponse, Hero> {
    override fun to(from: HeroResponse): Hero {
        return from.run {
            Hero(
                id = id,
                name = name,
                thumbnail = thumbnail.path,
                extension = thumbnail.extension,
                description = description
            )
        }
    }
}