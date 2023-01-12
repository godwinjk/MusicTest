package com.godwin.testmusic.ui

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.doesNotExist
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.ext.junit.rules.ActivityScenarioRule
import com.godwin.testmusic.R
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import org.junit.Rule
import org.junit.Test
import org.junit.rules.RuleChain

@HiltAndroidTest
internal class MainActivityTest {

    private val hiltRule = HiltAndroidRule(this)
    private val activityTestRule = ActivityScenarioRule(MainActivity::class.java)

    @get:Rule
    val rule: RuleChain = RuleChain
        .outerRule(hiltRule)
        .around(activityTestRule)

    @Test
    fun checkBottomNavDisplayed() {
        onView(withId(R.id.navigationView)).check(doesNotExist())
    }
    @Test
    fun checkLoadingViewIsDisplayed() {
        onView(withId(R.id.shimmerLayout)).check(matches(isDisplayed()))
    }
    @Test
    fun checkSearching() {
        onView(withId(R.id.incSearch)).check(matches(isDisplayed()))
    }
}