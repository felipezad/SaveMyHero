package com.exercise.savemyhero.navigation

import androidx.fragment.app.testing.launchFragmentInContainer
import androidx.navigation.Navigation
import androidx.navigation.testing.TestNavHostController
import androidx.test.core.app.ApplicationProvider
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers
import com.exercise.savemyhero.R
import com.exercise.savemyhero.ui.home.HomeFragment
import com.exercise.savemyhero.ui.home.list.HomeHeroListAdapter
import com.google.common.truth.Truth.assertThat
import org.junit.Test

class HomeHeroFragmentTest {

    @Test
    fun testNavigateFromHomeToHero() {

        val navController = TestNavHostController(ApplicationProvider.getApplicationContext())
        navController.setGraph(R.navigation.mobile_navigation)

        val homeScenario = launchFragmentInContainer<HomeFragment>()

        homeScenario.onFragment { fragment ->
            Navigation.setViewNavController(fragment.requireView(), navController)
        }
        Thread.sleep(5000)
        onView(ViewMatchers.withId(R.id.homeHeroRecyclerView))
            .perform(
                RecyclerViewActions.actionOnItemAtPosition<HomeHeroListAdapter.HomeHeroViewHolder>(
                    0,
                    click()
                )
            )
        val currentDestination = navController.backStack.last()

        assertThat(currentDestination.destination.id).isEqualTo(R.id.navigation_hero)
    }


}