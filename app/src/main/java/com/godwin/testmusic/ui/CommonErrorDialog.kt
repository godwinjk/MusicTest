package com.godwin.testmusic.ui

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.RawRes
import androidx.appcompat.app.AlertDialog
import com.airbnb.lottie.LottieDrawable
import com.godwin.testmusic.R
import com.godwin.testmusic.databinding.LayoutCommonErrorBinding
import com.godwin.testmusic.util.binding_adapters.visibility

class CommonErrorDialog {
    companion object {
        fun showError(
            context: Context,
            title: String,
            @RawRes lottieRes: Int? = null,
            subText: String? = "",
            onClick: (() -> Unit)? = null
        ): AlertDialog {
            val dialog = AlertDialog.Builder(context).create()
            val view = LayoutInflater.from(context).inflate(R.layout.layout_common_error, null)
            val binding = LayoutCommonErrorBinding.bind(view)

            dialog.setView(view)
            binding.tvTitle.text = title
            binding.tvSubText.text = subText
            lottieRes?.let {
                binding.lottieAnimView.visibility(true)
                binding.lottieAnimView.setAnimation(lottieRes)
                binding.lottieAnimView.repeatCount = LottieDrawable.INFINITE
                binding.lottieAnimView.playAnimation()
            }
            binding.btnOk.setOnClickListener {
                onClick?.invoke()
                dialog.dismiss()
            }
            dialog.show()
            dialog.window?.setLayout(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            return dialog
        }
    }
}