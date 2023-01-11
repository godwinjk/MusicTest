package com.godwin.testmusic.ui

import com.godwin.testmusic.model.Song

interface OnItemClickListener<T> {
    fun onClick(t: T?)
}