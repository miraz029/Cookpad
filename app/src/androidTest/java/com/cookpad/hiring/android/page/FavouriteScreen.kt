package com.cookpad.hiring.android.page

import android.content.res.Resources
import androidx.recyclerview.widget.RecyclerView
import androidx.test.espresso.ViewInteraction
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.platform.app.InstrumentationRegistry
import com.cookpad.hiring.android.R
import com.cookpad.hiring.android.core.testing.extension.*
import com.cookpad.hiring.android.core.testing.matcher.clickOnViewChild
import com.cookpad.hiring.android.core.testing.matcher.numberOfChildren
import org.hamcrest.Matchers

class FavouriteScreen {

    private var resources: Resources =
        InstrumentationRegistry.getInstrumentation().targetContext.resources

    private fun favouriteTab(): ViewInteraction =
        findView(R.id.navigation_favorite)

    fun selectFavouriteTab() {
        favouriteTab().waitFor {
            shouldBeClickable()
        }.click()

        findToolBar() shouldHaveText resources.getString(R.string.title_favorites)
    }

    fun waitTillLoadingCompleted() {
        findView(R.id.loadingCircularProgressIndicator)
            .perform(waitUntilNotShown(30000))
    }

    fun verifyFavouriteCount(count: Int) {
        findView(R.id.favoriteList).check(
            ViewAssertions.matches(
                Matchers.allOf(
                    numberOfChildren(
                        count
                    )
                )
            )
        )
    }

    fun removeAllFavouriteRecipes() {
        while (true) {
            try {
                findView(R.id.favoriteList)
                    .perform(
                        RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(
                            0,
                            clickOnViewChild(R.id.favouriteImageView)
                        )
                    )
            } catch (e: Exception) {
                break
            }
        }
    }
}