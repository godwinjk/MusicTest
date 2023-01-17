package com.godwin.testmusic.ui

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.IdlingRegistry
import androidx.test.espresso.IdlingResource
import androidx.test.espresso.assertion.ViewAssertions.doesNotExist
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.idling.CountingIdlingResource
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.ext.junit.rules.ActivityScenarioRule
import com.godwin.testmusic.R
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.RuleChain

@HiltAndroidTest
internal class MainActivityTest {

    private val hiltRule = HiltAndroidRule(this)
    private val activityTestRule = ActivityScenarioRule(MainActivity::class.java)

    private val idlingResource = CountingIdlingResource("Test")

    @get:Rule
    val rule: RuleChain = RuleChain
        .outerRule(hiltRule)
        .around(activityTestRule)

    @Before
    fun setUp() {
        IdlingRegistry.getInstance().register(idlingResource)
    }

    @Test
    fun checkBottomNavDisplayed() {
        idlingResource.increment()
        idlingResource.decrement()
        onView(withId(R.id.navigationView)).check(matches(isDisplayed()))
        onView(withId(R.id.shimmerLayout)).check(matches(isDisplayed()))
        onView(withId(R.id.incSearch)).check(matches(isDisplayed()))

    }

//    @Test
//    fun checkLoadingViewIsDisplayed() {
//        onView(withId(R.id.shimmerLayout)).check(matches(isDisplayed()))
//        idlingResource.increment()
//        idlingResource.decrement()
//    }
//
//    @Test
//    fun checkSearching() {
//        onView(withId(R.id.incSearch)).check(matches(isDisplayed()))
//        idlingResource.increment()
//        idlingResource.decrement()
//    }

    @After
    fun tearDown() {
        IdlingRegistry.getInstance().unregister(idlingResource)

    }

}