package com.example.movieapp.domain.model

data class MoviesDomain(
    val title: String,
    val posterPath: String,
    val originalTitle: String,
    val overview: String,
    val rating: Double,
    val releaseDate: String
)
