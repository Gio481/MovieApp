package com.example.movieapp.domain.interactor.usecases.get_movies_detail

import com.example.movieapp.domain.model.FavouriteMoviesDomain

interface GetMoviesDetailUseCase {
    suspend fun getAllMoviesID(): List<Int>
    suspend fun insertMovie(favouriteMoviesDomain: FavouriteMoviesDomain)
    suspend fun deleteMovie(movieId: Int)
}