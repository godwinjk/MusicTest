package com.godwin.testmusic.util.extensions

import android.content.Context
import android.view.inputmethod.InputMethodManager
import androidx.fragment.app.FragmentActivity


fun FragmentActivity.inputMethodService(): InputMethodManager {
    return this.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
}

fun FragmentActivity.hideSoftInput(flags: Int = 0) {
    this.inputMethodService().hideSoftInputFromWindow(this.currentFocus?.windowToken, flags)
}