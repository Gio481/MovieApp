package com.example.movieapp.domain.interactor.usecases.get_movies_detail

import com.example.movieapp.domain.model.MoviesDomain
import com.example.movieapp.domain.repository.movies_detail.MoviesDetailRepository

class GetMoviesDetailUseCaseImpl(private val repository: MoviesDetailRepository) :
    GetMoviesDetailUseCase {

    override suspend fun getAllMoviesPosterPath(): List<String> {
        return repository.getAllMoviesID()
    }

    override suspend fun insertMovie(favouriteMoviesDomain: MoviesDomain) {
        repository.insertMovie(favouriteMoviesDomain)
    }

    override suspend fun deleteMovie(posterPath: String) {
        repository.deleteMovie(posterPath)
    }
}