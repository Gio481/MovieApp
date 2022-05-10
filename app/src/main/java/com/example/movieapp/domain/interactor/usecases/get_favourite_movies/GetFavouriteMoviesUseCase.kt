package com.example.movieapp.domain.interactor.usecases.get_favourite_movies

import com.example.movieapp.domain.model.MoviesDomain

interface GetFavouriteMoviesUseCase {
    suspend fun getFavouriteMovies(): List<MoviesDomain>

}