package com.example.movieapp.util.extensions.image_view

import android.widget.ImageView
import com.bumptech.glide.Glide

fun ImageView.setImage(url: String) {
    Glide.with(this).load(url).into(this)
}