package com.example.movieapp.domain.repository.movies_detail

import com.example.movieapp.domain.model.MoviesDomain

interface MoviesDetailRepository {
    suspend fun getAllMoviesPoster(): List<String>
    suspend fun insertMovie(movie: MoviesDomain)
    suspend fun deleteMovie(posterPath: String)
}