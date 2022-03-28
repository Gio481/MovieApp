package com.example.movieapp.domain.repository.movies_detail

import com.example.movieapp.domain.model.MoviesDomain

interface MoviesDetailRepository {
    suspend fun getAllMoviesID(): List<Int>
    suspend fun insertMovie(movie: MoviesDomain)
    suspend fun deleteMovie(moviesId: Int)
}