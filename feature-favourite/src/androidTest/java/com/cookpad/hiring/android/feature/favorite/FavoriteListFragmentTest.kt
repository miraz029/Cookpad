package com.cookpad.hiring.android.feature.favorite

import androidx.recyclerview.widget.GridLayoutManager
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
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
@ExperimentalCoroutinesApi
@HiltAndroidTest
class FavoriteListFragmentTest {

    @get: Rule
    var hiltRule = HiltAndroidRule(this)

    @Before
    fun init() {
        hiltRule.inject()
    }

    @ExperimentalCoroutinesApi
    @Test
    fun test_Favourite_Fragment_Loading_State() {
        runTest {

            launchFragmentInHiltContainer<FavoriteListFragment> {
                val rv = view?.findViewById<RecyclerView>(R.id.favoriteList)

                assertEquals(
                    RecyclerView.VERTICAL,
                    (rv?.layoutManager as GridLayoutManager).orientation
                )
            }
            onView(withId(R.id.loadingCircularProgressIndicator)).check(matches(isDisplayed()))
            onView(withId(R.id.favoriteList)).check(matches(isDisplayed()))
        }
    }
}


