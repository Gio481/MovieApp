package com.example.movieapp.domain.repository.favourite_movies

import com.example.movieapp.domain.model.MoviesDomain

interface FavouriteMoviesRepository {
    suspend fun getFavouriteMovies(): List<MoviesDomain>
}