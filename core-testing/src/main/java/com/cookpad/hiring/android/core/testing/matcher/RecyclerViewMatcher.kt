package com.cookpad.hiring.android.core.testing.matcher

import android.annotation.SuppressLint
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.UiController
import androidx.test.espresso.ViewAction
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.BoundedMatcher
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.internal.util.Checks
import com.cookpad.hiring.android.core.testing.extension.waitFor
import org.hamcrest.Description
import org.hamcrest.Matcher

fun scrollRecyclerTo(recyclerViewId: Int, position: Int) {
    onView(withId(recyclerViewId)).waitFor {
        perform(
            RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(
                position
            )
        )
    }
}

fun clickOnViewChild(viewId: Int) = object : ViewAction {
    override fun getConstraints() = null

    override fun getDescription() = "Click on a child view with specified id."

    override fun perform(uiController: UiController, view: View) =
        click().perform(uiController, view.findViewById<View>(viewId))
}

@SuppressLint("RestrictedApi")
fun numberOfChildren(count: Int): Matcher<View> {
    Checks.checkNotNull(count)
    return object :
        BoundedMatcher<View, RecyclerView>(androidx.recyclerview.widget.RecyclerView::class.java) {
        override fun describeTo(p0: Description?) {
            p0?.appendText("has $count number of children")
        }

        override fun matchesSafely(item: RecyclerView?): Boolean {
            return count == item!!.adapter!!.itemCount
        }
    }
}