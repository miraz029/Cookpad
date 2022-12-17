package com.cookpad.hiring.android.core.testing.extension

import android.widget.TextView
import androidx.appcompat.widget.Toolbar
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.ViewInteraction
import androidx.test.espresso.matcher.ViewMatchers.*
import org.hamcrest.CoreMatchers.instanceOf
import org.hamcrest.Matchers.`is`
import org.hamcrest.Matchers.allOf

fun findToolBar(): ViewInteraction {
    return onView(
        allOf(
            instanceOf(TextView::class.java),
            withParent(instanceOf(Toolbar::class.java)),
            withEffectiveVisibility(Visibility.VISIBLE)
        )
    )
}

fun findViewWithTag(tag: String): ViewInteraction {
    return onView(
        withTagValue(
            `is`(tag)
        )
    )
}

fun findView(id: Int): ViewInteraction {
    return onView(withId(id))
}