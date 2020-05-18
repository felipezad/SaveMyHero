package com.exercise.savemyhero.domain.hero

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.exercise.savemyhero.domain.hero.Hero.Companion.TABLE_NAME

@Entity(tableName = TABLE_NAME)
data class Hero(

    @PrimaryKey
    var id: Int? = 0,
    var name: String = "",
    var thumbnail: String = "",
    var description: String = ""
) {
    companion object {
        const val TABLE_NAME = "hero"
    }
}