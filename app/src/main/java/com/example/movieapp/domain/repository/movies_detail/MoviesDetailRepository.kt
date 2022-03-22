package com.example.movieapp.domain.repository.movies_detail

import com.example.movieapp.domain.model.FavouriteMoviesDomain

interface MoviesDetailRepository {
    suspend fun insertMovie(movie: FavouriteMoviesDomain)
    suspend fun deleteMovie(moviesId: Long)
}