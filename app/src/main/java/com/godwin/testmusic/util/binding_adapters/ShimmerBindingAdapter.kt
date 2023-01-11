package com.godwin.testmusic.util.binding_adapters

import androidx.databinding.BindingAdapter
import com.facebook.shimmer.ShimmerFrameLayout

@BindingAdapter("android:isShimming")
fun ShimmerFrameLayout.isShimming(isShimming: Boolean) {
    if (isShimming) {
        startShimmer()
    } else {
        stopShimmer()
    }
}