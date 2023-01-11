package com.godwin.testmusic.util.extensions

import android.os.Bundle
import android.view.View
import android.view.inputmethod.InputMethodManager
import androidx.annotation.IdRes
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController


fun Fragment.navigate(@IdRes id: Int, args: Bundle? = null) {
    findNavController().navigate(id, args)
}

fun Fragment.popBack() {
    findNavController().popBackStack()
}

fun Fragment.showSoftInput(view: View, flags: Int = 0) {
    requireActivity()?.let {
        it.inputMethodService()
            .toggleSoftInputFromWindow(view.windowToken, InputMethodManager.SHOW_FORCED, flags)
    }
}

fun Fragment.hideSoftInput(flags: Int = 0) {
    requireActivity()?.let {
        it.inputMethodService().hideSoftInputFromWindow(it.currentFocus?.windowToken, flags)
    }
}