package com.example.movieapp.util.extensions.dialog

import android.app.Dialog
import android.content.Context
import android.view.LayoutInflater
import android.view.Window
import android.view.WindowManager
import androidx.viewbinding.ViewBinding
import com.example.movieapp.databinding.CustomDialogBinding

fun Dialog.setUpDialogLayout(binding: ViewBinding) {
    with(window!!) {
        setBackgroundDrawableResource(android.R.color.transparent)
        requestFeature(Window.FEATURE_NO_TITLE)
    }
    val params = window?.attributes
    with(params!!) {
        width = WindowManager.LayoutParams.MATCH_PARENT
        height = WindowManager.LayoutParams.MATCH_PARENT
    }
    setContentView(binding.root)
}

fun Context.createDialog(text: String) {
    val dialog = Dialog(this)
    val dialogBinding = CustomDialogBinding.inflate(LayoutInflater.from(this))
    dialog.apply {
        setUpDialogLayout(dialogBinding)
        with(dialogBinding) {
            errorText.text = text
            loadDataButton.setOnClickListener {
                dismiss()
            }
        }
    }
    dialog.show()
}