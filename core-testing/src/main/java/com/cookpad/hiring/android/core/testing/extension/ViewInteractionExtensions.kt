package com.cookpad.hiring.android.core.testing.extension

import android.os.SystemClock
import androidx.test.espresso.ViewInteraction
import androidx.test.espresso.action.ViewActions
import java.lang.Thread.sleep

inline fun <T> retry(timeOut: Double = 15.0, retryInterval: Double = 1.0, block: () -> T): T {
    val endTime = System.currentTimeMillis() + (timeOut * 1000).toLong()
    while (System.currentTimeMillis() < endTime) {
        try {
            return block()
        } catch (t: Throwable) {
            SystemClock.sleep((1000 * retryInterval).toLong())
        }
    }
    return block()
}

fun ViewInteraction.waitFor(timeOut: Double = 30.0, validations: ViewInteraction.(Any?) -> (Unit)): ViewInteraction {
    retry(timeOut, 1.0) {
        validations(this)
    }
    return this
}

fun ViewInteraction.click(time: Long = 0L): ViewInteraction {
    waitFor {
        shouldBeDisplayed()
        perform(ViewActions.click())
        if (time > 0) {
            sleep(time)
        }
    }
    return this
}






