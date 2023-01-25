package com.godwin.testmusic.ui

import androidx.test.core.app.ApplicationProvider
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.IdlingRegistry
import androidx.test.espresso.IdlingResource
import androidx.test.espresso.assertion.ViewAssertions.doesNotExist
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.idling.CountingIdlingResource
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.LargeTest
import com.godwin.testmusic.MainDispatcherRule
import com.godwin.testmusic.MainTestRunner
import com.godwin.testmusic.R
import com.godwin.testmusic.di.AppModule
import com.godwin.testmusic.di.NetworkModule
import com.godwin.testmusic.network.ICloudApiManager
import com.godwin.testmusic.util.NetworkConnectionManager
import com.godwin.testmusic.util.getOrAwaitValue
import dagger.hilt.android.testing.BindValue
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import dagger.hilt.android.testing.UninstallModules
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.runTest
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.RuleChain
import org.junit.runner.RunWith
import javax.inject.Inject

@HiltAndroidTest
//@RunWith(MainTestRunner::class)
//@UninstallModules(AppModule::class,NetworkModule::class)
@ExperimentalCoroutinesApi
@LargeTest
internal class MainActivityTest {

    private val hiltRule = HiltAndroidRule(this)
    private val activityTestRule = ActivityScenarioRule(MainActivity::class.java)
    private val mainDispatcherRule = MainDispatcherRule()

    private val idlingResource = CountingIdlingResource("Test")

    @get:Rule
    val rule: RuleChain = RuleChain
        .outerRule(hiltRule)
        .around(activityTestRule)
        .around(mainDispatcherRule)

    //    @Inject
    lateinit var viewModel: MainActivityViewModel

    @Inject
    lateinit var networkConnectionManager: NetworkConnectionManager

    @Inject
    lateinit var iCloudApiManager: ICloudApiManager

    @Before
    fun setUp() {
        hiltRule.inject()
        viewModel = MainActivityViewModel(networkConnectionManager, iCloudApiManager)
    }

    @Test
    fun checkBottomNavDisplayed() = runTest {
        onView(withId(R.id.navigationView)).check(matches(isDisplayed()))

    }

    @Test
    fun checkListLoaded() {
//        val list = runBlocking(context = Dispatchers.Main) {
//            viewModel.songEntryLiveData.getOrAwaitValue()
//        }
        onView(withId(R.id.rvSongs)).check(matches(isDisplayed()))

    }

    @After
    fun tearDown() {
        IdlingRegistry.getInstance().unregister(idlingResource)

    }

}