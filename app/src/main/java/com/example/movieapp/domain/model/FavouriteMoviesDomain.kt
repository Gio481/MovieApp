package com.example.movieapp.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class FavouriteMoviesDomain(
    val id: Int,
    val poster: String,
    val releaseDate: String,
    val rating: Double,
    val originalTitle: String,
    val overview: String,
    val title: String,
) : Parcelable