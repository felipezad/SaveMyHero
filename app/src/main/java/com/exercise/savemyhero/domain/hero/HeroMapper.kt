package com.exercise.savemyhero.domain.hero

import com.exercise.savemyhero.data.remote.model.HeroResponse
import com.exercise.savemyhero.domain.Mapper

class HeroMapper : Mapper<HeroResponse, Hero> {
    override fun to(from: HeroResponse): Hero {
        TODO("Not yet implemented")
    }
}