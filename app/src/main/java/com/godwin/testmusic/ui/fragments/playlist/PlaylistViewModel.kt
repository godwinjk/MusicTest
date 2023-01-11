package com.godwin.testmusic.ui.fragments.playlist

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.godwin.testmusic.model.Song
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class PlaylistViewModel : ViewModel() {

    val favouriteList = MutableLiveData<List<Song>>()
    val searchLiveData = MutableLiveData<String>()

    /**
     * Heavy task need to isolate from main thread
     */
    fun sortFavourite(songs: List<Song>) {
        viewModelScope.launch(context = Dispatchers.IO) {
            favouriteList.postValue(songs.filter { it.isFav })
        }
    }


    fun filterSearch(query: String, list: List<Song>): List<Song> {
        return list.filter { it.song.imName.label.lowercase().startsWith(query.lowercase()) }
    }
}