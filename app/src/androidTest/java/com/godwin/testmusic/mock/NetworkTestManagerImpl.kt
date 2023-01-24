package com.godwin.testmusic.mock

import com.godwin.testmusic.util.NetworkConnectionManager
import kotlinx.coroutines.flow.MutableStateFlow

class NetworkTestManagerImpl : NetworkConnectionManager {
    override val isNetworkConnectedFlow = MutableStateFlow(true)
    override val isNetworkConnected: Boolean
        get() = true

    override fun startListenNetworkState() {

    }

    override fun stopListenNetworkState() {
    }
}