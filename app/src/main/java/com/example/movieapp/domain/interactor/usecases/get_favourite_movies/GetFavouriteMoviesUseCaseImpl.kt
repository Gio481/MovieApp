package com.example.movieapp.domain.interactor.usecases.get_favourite_movies

import com.example.movieapp.domain.model.MoviesDomain
import com.example.movieapp.domain.repository.favourite_movies.FavouriteMoviesRepository

class GetFavouriteMoviesUseCaseImpl(private val repository: FavouriteMoviesRepository) :
    GetFavouriteMoviesUseCase {

    override suspend fun getFavouriteMovies(): List<MoviesDomain> {
        return repository.getFavouriteMovies()
    }
}