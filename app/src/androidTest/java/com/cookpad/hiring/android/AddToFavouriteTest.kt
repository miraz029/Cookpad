package com.cookpad.hiring.android

import androidx.test.core.app.ActivityScenario
import com.cookpad.hiring.android.page.FavouriteScreen
import com.cookpad.hiring.android.page.RecipeScreen
import com.cookpad.hiring.android.ui.MainActivity
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import java.util.function.Supplier

@HiltAndroidTest
class AddToFavouriteTest {

    @get:Rule(order = 0)
    var hiltRule = HiltAndroidRule(this)

    private var scenarioSupplier: Supplier<ActivityScenario<MainActivity>> =
        Supplier<ActivityScenario<MainActivity>> { ActivityScenario.launch(MainActivity::class.java) }

    private var scenario: ActivityScenario<MainActivity>? = null

    @Before
    fun before() {
        scenario = scenarioSupplier.get()
    }

    @After
    fun after() {
        scenario?.close()
    }

    @Test
    fun test_Add_Recipe_In_Favourite_List() {

        // wait till recipe collection loading is completed
        with(RecipeScreen()) {
            waitTillLoadingCompleted()
        }

        // remove all favourite recipes
        with(FavouriteScreen()) {
            selectFavouriteTab()
            waitTillLoadingCompleted()
            removeAllFavouriteRecipes()
        }

        // save first recipe as favourite
        with(RecipeScreen()) {
            waitTillLoadingCompleted()
            selectRecipeTab()
            saveRecipeAsFavourite(0)
        }

        // confirm item saved as favourite
        with(FavouriteScreen()) {
            selectFavouriteTab()
            verifyFavouriteCount(1)
        }
    }
}