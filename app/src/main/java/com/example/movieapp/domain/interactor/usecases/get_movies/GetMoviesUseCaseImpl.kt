package com.example.movieapp.domain.interactor.usecases.get_movies

import com.example.movieapp.domain.repository.movies.MoviesRepository

class GetMoviesUseCaseImpl(private val repository: MoviesRepository) : GetMoviesUseCase {
    override suspend fun getPopularMovies(page: Int) {
        repository.getPopularMovies(page)
    }

    override suspend fun getTopRatedMovies(page: Int) {
        repository.getTopRatedMovies(page)
    }

    override suspend fun getFavouriteMovies() {
        repository.getFavouriteMovies()
    }
}