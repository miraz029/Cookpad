package com.cookpad.hiring.android.feature.recipe

import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.ext.junit.runners.AndroidJUnit4
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import junit.framework.TestCase.assertEquals
import junit.framework.TestCase.assertTrue
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith


@RunWith(AndroidJUnit4::class)
@ExperimentalCoroutinesApi
@HiltAndroidTest
class RecipeListFragmentTest {

    @get: Rule
    var hiltRule = HiltAndroidRule(this)

    @Before
    fun init() {
        hiltRule.inject()
    }

    @ExperimentalCoroutinesApi
    @Test
    fun test_Recipe_Fragment_Loading_State() {
        runTest {

            launchFragmentInHiltContainer<RecipeListFragment> {
                val rv = view?.findViewById<RecyclerView>(R.id.recipeList)

                assertEquals(
                    RecyclerView.VERTICAL,
                    (rv?.layoutManager as LinearLayoutManager).orientation
                )
            }
            onView(withId(R.id.loadingCircularProgressIndicator)).check(matches(isDisplayed()))
            onView(withId(R.id.recipeList)).check(matches(isDisplayed()))
        }
    }
}


