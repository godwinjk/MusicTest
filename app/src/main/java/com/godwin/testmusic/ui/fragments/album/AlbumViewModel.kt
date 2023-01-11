package com.godwin.testmusic.ui.fragments.album

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.godwin.testmusic.model.Song

class AlbumViewModel : ViewModel() {

    val searchLiveData = MutableLiveData<String>()

    fun filterSearch(query: String, list: List<Song>): List<Song> {
        return list.filter { it.song.imName.label.lowercase().startsWith(query.lowercase()) }
    }
}