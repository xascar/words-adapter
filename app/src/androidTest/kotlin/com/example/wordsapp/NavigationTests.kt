package com.example.wordsapp

import androidx.fragment.app.testing.FragmentScenario
import androidx.fragment.app.testing.launchFragmentInContainer
import androidx.navigation.Navigation
import androidx.navigation.testing.TestNavHostController
import androidx.recyclerview.widget.RecyclerView
import androidx.test.core.app.ApplicationProvider
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.ext.junit.runners.AndroidJUnit4
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class NavigationTests {

    lateinit var navController: TestNavHostController

    lateinit var exampleFragmentScenario: FragmentScenario<LetterListFragment>

    @Before
    fun setup(){
        navController = TestNavHostController(
            ApplicationProvider.getApplicationContext()
        )

        exampleFragmentScenario =  launchFragmentInContainer(themeResId=R.style.Theme_Words)

        exampleFragmentScenario.onFragment { fragment ->

            navController.setGraph(R.navigation.nav_graph)

            Navigation.setViewNavController(fragment.requireView(),  navController)
        }
    }

    @Test
    fun navigate_to_words_nav_component(){
        /*
        //Instance of navigation controller
        val navController = TestNavHostController(
            ApplicationProvider.getApplicationContext()
        )

        // Using ActivityScenarioRule equivalence for fragments specifying theming
        val letterListScenario = launchFragmentInContainer<LetterListFragment>(themeResId = R.style.Theme_Words)

        // Specifying which NavGraph will use the NavController for the fragment launched
        letterListScenario.onFragment { fragment ->

            navController.setGraph(R.navigation.nav_graph)

            Navigation.setViewNavController(fragment.requireView(), navController)
        }
        */

        //triggering the event that prompts the navigation
        onView(withId(R.id.recycler_view))
            .perform(
                RecyclerViewActions
                .actionOnItemAtPosition<RecyclerView.ViewHolder>(2, click()))

        //check to make sure that the current navigation controller's destination has the ID of the fragment we expect to be in
        assertEquals(navController.currentDestination?.id, R.id.wordListFragment)


    }

}