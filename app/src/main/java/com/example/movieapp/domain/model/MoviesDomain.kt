package com.example.movieapp.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class MoviesDomain(
    val id: Int,
    val title: String,
    private val posterPath: String,
    val posterUrl: String = "https://image.tmdb.org/t/p/w185$posterPath",
    val originalTitle: String,
    val overview: String,
    val rating: Double,
    val releaseDate: String,
) : Parcelable
