package com.exercise.savemyhero.data.local

import androidx.room.*
import com.exercise.savemyhero.domain.hero.Hero
import kotlinx.coroutines.flow.Flow

@Dao
interface HeroDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(hero: Hero)

    @Delete
    suspend fun delete(hero: Hero)

    @Query("SELECT * FROM ${Hero.TABLE_NAME}")
    fun getFavoriteHeroes(): Flow<List<Hero>>

}