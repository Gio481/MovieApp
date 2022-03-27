package com.example.movieapp.domain.model

data class FavouriteMoviesDomain(
    val id: Int,
    val poster: String,
    val releaseDate: String,
    val rating: Double,
)