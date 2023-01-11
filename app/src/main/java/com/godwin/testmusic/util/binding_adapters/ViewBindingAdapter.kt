package com.godwin.testmusic.util.binding_adapters

import android.view.View
import androidx.appcompat.widget.AppCompatImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.godwin.testmusic.R
import com.google.android.material.imageview.ShapeableImageView

@BindingAdapter("android:visibility", requireAll = true)
fun View.visibility(visibility: Boolean) {
    this.visibility = if (visibility) View.VISIBLE else View.GONE
}

@BindingAdapter("android:img")
fun AppCompatImageView.setImage(imgUri: String?) {
    imgUri?.let {
        Glide.with(this).load(imgUri).placeholder(R.drawable.ic_baseline_music_note_256).fitCenter()
            .into(this)
    }

}