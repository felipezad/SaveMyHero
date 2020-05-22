package com.exercise.savemyhero.repository

import com.exercise.savemyhero.common.ActionResult
import com.exercise.savemyhero.common.Failure
import com.exercise.savemyhero.common.Loading
import com.exercise.savemyhero.common.Success
import com.exercise.savemyhero.data.local.HeroDao
import com.exercise.savemyhero.data.remote.MarvelService
import com.exercise.savemyhero.data.remote.model.ApiResponse
import com.exercise.savemyhero.data.remote.model.ApiResponseJsonAdapter
import com.exercise.savemyhero.data.remote.model.HeroResponse
import com.exercise.savemyhero.domain.hero.Hero
import com.exercise.savemyhero.domain.hero.HeroMapper
import com.exercise.savemyhero.domain.hero.HeroRepository
import com.squareup.moshi.Moshi
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.impl.annotations.MockK
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runBlockingTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Before
import org.junit.Test
import java.io.IOException

class HeroRepositoryTest {

    private val testDispatcher = TestCoroutineDispatcher()

    @MockK
    lateinit var marvelService: MarvelService

    @MockK
    lateinit var heroMapper: HeroMapper

    @MockK
    lateinit var heroDao: HeroDao

    private lateinit var heroRepository: HeroRepository
    private val heroOne = Hero(1, "hero1", "no thumb1")
    private val heroTwo = Hero(2, "hero2", "no thumb2")
    private val listOfHeroes = listOf(heroOne, heroTwo)
    private var heroMock: ApiResponse<HeroResponse>? = null
    private val moshi = Moshi.Builder().build()
    private val apiResponse =
        ApiResponseJsonAdapter<HeroResponse>(moshi, arrayOf(HeroResponse::class.java))
    private var response: MutableList<ActionResult<List<Hero>>> = mutableListOf()

    @Before
    fun setUp() {
        response.clear()
        heroMock = apiResponse.fromJson(HERO_RESPONSE_JSON_STRING)
        MockKAnnotations.init(this)
        Dispatchers.setMain(testDispatcher)
        heroRepository = HeroRepository(heroMapper, marvelService, heroDao)
        assertNotNull(heroRepository)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
        testDispatcher.cleanupTestCoroutines()
    }

    @Test
    fun `Repository only request once api service with successful response`() {
        runBlockingTest {
            heroMock?.let {
                coEvery { marvelService.requestHeroes(offset = 1) } returns it
                coEvery { heroMapper.to(from = it.data.results) } returns listOfHeroes

                heroRepository.getElementsFromApi(1).collect { states ->
                    response.add(states)
                }
                advanceTimeBy(1_000)
                coVerify(exactly = 1) {
                    marvelService.requestHeroes(offset = 1)
                    heroMapper.to(from = it.data.results)
                }
                assertEquals(3, response.size)
                assert(response[0] is Loading)
                assert(response[1] is Success)
                assert(response[2] is Loading)
            }
        }
    }

    @Test
    fun `Repository only request once api service with network failure`() {
        runBlockingTest {
            coEvery { marvelService.requestHeroes(offset = 1) } throws IOException()
            coEvery { heroMapper.to(from = heroMock?.data!!.results) }

            heroRepository.getElementsFromApi(1).collect {
                response.add(it)
            }
            advanceTimeBy(1_000)
            coVerify(exactly = 1) {
                marvelService.requestHeroes(offset = 1)
            }
            assertEquals(3, response.size)
            assert(response[0] is Loading)
            assert(response[1] is Failure)
            assert(response[2] is Loading)
        }
    }
}