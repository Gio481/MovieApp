package com.example.movieapp.domain.interactor.usecases.get_favourite_movies

import com.example.movieapp.domain.model.FavouriteMoviesDomain

interface GetFavouriteMoviesUseCase {
    suspend fun getFavouriteMovies(): List<FavouriteMoviesDomain>

}