package com.godwin.testmusic.network

import com.godwin.testmusic.model.Song
import com.godwin.testmusic.network.model.Failed
import com.godwin.testmusic.network.model.SongResult
import com.godwin.testmusic.network.model.Success
import retrofit2.HttpException
import javax.inject.Inject


class ICloudApiManagerImpl @Inject constructor(private val iCloudApiService: ICloudApiService) :
    ICloudApiManager {

    override suspend fun fetchSongs(): SongResult {
        try {
            val response = iCloudApiService.fetchSongs()
            val songs =response.feed.entry.map { Song(it) }
            return Success(songs)
        } catch (e: Exception) {
            e.printStackTrace()
            if (e is HttpException) {
                return Failed(e.message())
            }
        }
        return Failed("Something went wrong")
    }
}