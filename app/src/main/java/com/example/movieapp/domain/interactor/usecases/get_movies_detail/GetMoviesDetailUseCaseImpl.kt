package com.example.movieapp.domain.interactor.usecases.get_movies_detail

import com.example.movieapp.domain.model.FavouriteMoviesDomain
import com.example.movieapp.domain.repository.movies_detail.MoviesDetailRepository

class GetMoviesDetailUseCaseImpl(private val repository: MoviesDetailRepository) :
    GetMoviesDetailUseCase {

    override suspend fun insertMovie(favouriteMoviesDomain: FavouriteMoviesDomain) {
        repository.insertMovie(favouriteMoviesDomain)
    }

    override suspend fun deleteMovie(movieId: Long) {
        repository.deleteMovie(movieId)
    }
}