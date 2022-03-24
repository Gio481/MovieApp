package com.example.movieapp.util.extensions.image_view

import android.widget.ImageView
import com.bumptech.glide.Glide
import com.example.movieapp.util.Constants.IMAGE_BASE_URL

fun ImageView.setImage(url: String) {
    Glide.with(this).load(IMAGE_BASE_URL + url).into(this)
}