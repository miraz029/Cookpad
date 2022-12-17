package com.cookpad.hiring.android.core.testing.extension

import android.view.View
import androidx.test.espresso.PerformException
import androidx.test.espresso.UiController
import androidx.test.espresso.ViewAction
import androidx.test.espresso.ViewInteraction
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.espresso.util.HumanReadables
import org.hamcrest.CoreMatchers.any
import org.hamcrest.Matcher
import java.util.concurrent.TimeoutException

infix fun ViewInteraction.shouldHaveText(expectedText: String) {
    waitFor {
        check(matches(withText(expectedText)))
    }
}

fun ViewInteraction.shouldBeDisplayed() {
    check(matches(isDisplayed()))
}

fun ViewInteraction.shouldBeClickable() {
    check(matches(isClickable()))
}

fun waitUntilNotShown(millis: Long): ViewAction {
    return object : ViewAction {
        override fun getConstraints(): Matcher<View> {
            return any(View::class.java)
        }

        override fun getDescription(): String {
            return "Wait for a specific view to be hidden, for a max of $millis ms."
        }

        override fun perform(uiController: UiController, view: View) {
            uiController.loopMainThreadUntilIdle()

            val startTime = System.currentTimeMillis()
            val endTime = startTime + millis

            do {
                if (!view.isShown) {
                    return
                }
                uiController.loopMainThreadForAtLeast(100)
            } while (System.currentTimeMillis() < endTime)

            throw PerformException.Builder()
                .withActionDescription(this.description)
                .withViewDescription(HumanReadables.describe(view))
                .withCause(TimeoutException())
                .build()
        }
    }
}