package com.godwin.testmusic.mock

import android.content.Context
import com.godwin.testmusic.util.NetworkConnectionManager
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.MutableStateFlow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class NetworkTestManagerImpl @Inject constructor() : NetworkConnectionManager {
    override val isNetworkConnectedFlow = MutableStateFlow(true)
    override val isNetworkConnected: Boolean
        get() = true

    override fun startListenNetworkState() {

    }

    override fun stopListenNetworkState() {
    }
}