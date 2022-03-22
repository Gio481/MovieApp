package com.example.movieapp.domain.model

data class FavouriteMoviesDomain(
    val id: Long,
    val poster: String,
    val releaseDate: String,
    val rating: Double
) {
    fun rating(): String {
        return rating.toString()
    }
}