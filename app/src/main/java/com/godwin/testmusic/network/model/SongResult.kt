package com.godwin.testmusic.network.model

import com.godwin.testmusic.model.Song

sealed class SongResult
data class Success(val response: List<Song>) : SongResult()
data class Failed(val error: String) : SongResult()