package com.godwin.testmusic.ui

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.godwin.testmusic.model.CommonError
import com.godwin.testmusic.model.Song
import com.godwin.testmusic.network.ICloudApiManager
import com.godwin.testmusic.network.ICloudApiService
import com.godwin.testmusic.network.model.Entry
import com.godwin.testmusic.network.model.Failed
import com.godwin.testmusic.network.model.Feed
import com.godwin.testmusic.network.model.Success
import com.godwin.testmusic.util.NetworkConnectionManager
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainActivityViewModel @Inject constructor(
    private val networkConnectionManager: NetworkConnectionManager,
    private val icloudManager: ICloudApiManager
) : ViewModel() {

    val commonError = MutableLiveData<CommonError?>()

    val isFetching = MutableLiveData<Boolean>(false)

    val songEntryLiveData = MutableLiveData<List<Song>>()
    val selectedLiveData = MutableLiveData<Song>()

    init {
        networkConnectionManager.isNetworkConnectedFlow.onEach {
            if (!it && songEntryLiveData.value == null) commonError.postValue(CommonError.noInternet())
            if (it) {
                fetchSongs()
            }
        }.launchIn(viewModelScope)

        fetchSongs()
    }

    fun startListeningNetworkState() {
        networkConnectionManager.startListenNetworkState()
    }

    fun stopListeningNetworkState() {
        networkConnectionManager.stopListenNetworkState()
    }

    fun fetchSongs() {
        viewModelScope.launch(context = Dispatchers.IO) {
            isFetching.postValue(true)
            when (val result = icloudManager.fetchSongs()) {
                is Success -> {
                    songEntryLiveData.postValue(result.response)
                    commonError.postValue(null)
                }
                is Failed -> {
                    commonError.postValue(CommonError.fetchingError())
                }
            }
            isFetching.postValue(false)
        }
    }
}