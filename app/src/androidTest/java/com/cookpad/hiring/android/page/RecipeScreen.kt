package com.cookpad.hiring.android.page

import android.content.res.Resources
import androidx.recyclerview.widget.RecyclerView
import androidx.test.espresso.ViewInteraction
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.platform.app.InstrumentationRegistry
import com.cookpad.hiring.android.R
import com.cookpad.hiring.android.core.testing.extension.*
import com.cookpad.hiring.android.core.testing.matcher.clickOnViewChild
import com.cookpad.hiring.android.core.testing.matcher.scrollRecyclerTo

class RecipeScreen {

    private var resources: Resources =
        InstrumentationRegistry.getInstrumentation().targetContext.resources

    private fun recipeTab(): ViewInteraction = findView(R.id.navigation_recipes)

    fun selectRecipeTab() {
        recipeTab().waitFor {
            shouldBeClickable()
        }.click()

        findToolBar() shouldHaveText resources.getString(R.string.title_recipes)
    }

    fun waitTillLoadingCompleted() {
        findView(R.id.loadingCircularProgressIndicator)
            .perform(waitUntilNotShown(30000))
    }

    fun saveRecipeAsFavourite(itemPosition: Int) {
        scrollRecyclerTo(R.id.recipeList, itemPosition)
        findView(R.id.recipeList).perform(
            RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(
                itemPosition,
                clickOnViewChild(R.id.favouriteImageView)
            )
        )
    }
}