package com.exercise.savemyhero.database

import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import com.exercise.savemyhero.data.local.SaveMyHeroDatabase
import com.exercise.savemyhero.domain.hero.Hero
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import org.hamcrest.CoreMatchers.equalTo
import org.hamcrest.MatcherAssert.assertThat
import org.junit.After
import org.junit.Before
import org.junit.Test

class HeroDaoTest {

    private lateinit var databaseMock: SaveMyHeroDatabase

    @Before
    fun setUp() {
        databaseMock = Room.inMemoryDatabaseBuilder(
            ApplicationProvider.getApplicationContext(),
            SaveMyHeroDatabase::class.java
        ).build()
    }

    @Test
    @Throws(InterruptedException::class)
    fun insertHeroAndSelect() = runBlocking {
        val hero = Hero(1, "mock", "mock", "mock", "mock")
        databaseMock.heroDao().insert(hero)

        val heroList = databaseMock.heroDao().getFavoriteHeroes().first()

        assertThat(heroList, equalTo(listOf(hero)))
    }

    @Test
    @Throws(InterruptedException::class)
    fun insertHeroAndDelete() = runBlocking {
        val hero = Hero(1, "mock", "mock", "mock", "mock")
        databaseMock.heroDao().insert(hero)
        databaseMock.heroDao().delete(hero)

        val heroList = databaseMock.heroDao().getFavoriteHeroes().first()

        assertThat(heroList, equalTo(listOf()))
    }


    @After
    fun tearDown() {
        databaseMock.close()
    }
}