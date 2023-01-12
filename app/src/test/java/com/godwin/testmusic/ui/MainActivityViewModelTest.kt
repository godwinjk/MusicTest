package com.godwin.testmusic.ui

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.viewModelScope
import com.godwin.testmusic.mock.ICloudTestManager
import com.godwin.testmusic.mock.NetworkTestManagerImpl
import com.godwin.testmusic.MainDispatcherRule
import com.godwin.testmusic.model.Song
import com.godwin.testmusic.network.ICloudApiManager
import com.godwin.testmusic.util.NetworkConnectionManager
import com.godwin.testmusic.util.getOrAwaitValue
import dagger.hilt.android.testing.HiltAndroidTest
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.delay
import kotlinx.coroutines.newCoroutineContext
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.runTest
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@HiltAndroidTest
@ExperimentalCoroutinesApi
class MainActivityViewModelTest {

    @get:Rule
    val mainDispatcherRule = MainDispatcherRule()

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    private val networkConnectionManager: NetworkConnectionManager = NetworkTestManagerImpl()
    private val icloudApuManager: ICloudApiManager = ICloudTestManager()

    private lateinit var viewModelTest: MainActivityViewModel

    @Before
    fun setup() {
        viewModelTest = MainActivityViewModel(networkConnectionManager, icloudApuManager)
        viewModelTest.fetchSongs()
    }

    @Test
    fun checkFetchSongs() = runTest(mainDispatcherRule.dispatcher) {

        val list= viewModelTest.songEntryLiveData.getOrAwaitValue()
        assert(list != null)
    }

    @Test
    fun checkSelectedSong() = runTest(mainDispatcherRule.dispatcher) {

        val list= viewModelTest.songEntryLiveData.getOrAwaitValue()
        viewModelTest.selectedLiveData.postValue(list[0])
        val selectedSong =viewModelTest.selectedLiveData.getOrAwaitValue()
        assert(selectedSong != null)
    }
    @After
    fun tearDown() {

    }

}