package com.godwin.testmusic.model

import androidx.annotation.RawRes
import com.godwin.testmusic.R

data class CommonError(val code: Int, val message: String) {
    @RawRes
    var lottieRes: Int? = null

    companion object {
        const val NO_INTERNET = 1
        const val FETCHING_ERROR = 2
        fun noInternet() = CommonError(NO_INTERNET, "Not connected to network").apply {
            lottieRes = R.raw.lottie_no_connection
        }

        fun fetchingError() = CommonError(FETCHING_ERROR, "Can't fetch data")
    }
}


