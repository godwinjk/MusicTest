package com.godwin.testmusic.model

import android.os.Parcelable
import com.godwin.testmusic.network.model.Entry
import kotlinx.parcelize.Parcelize

@Parcelize
data class Song(val song: Entry, var isFav: Boolean = false) : Parcelable
