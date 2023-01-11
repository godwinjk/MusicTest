package com.godwin.testmusic.network

import com.godwin.testmusic.network.model.Failed
import com.godwin.testmusic.network.model.SongResult
import com.godwin.testmusic.network.model.Success
import retrofit2.HttpException
import javax.inject.Inject


interface ICloudApiManager {

    suspend fun fetchSongs(): SongResult
}