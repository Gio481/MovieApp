package com.example.movieapp.domain.interactor.usecases.get_movies

import com.example.movieapp.domain.model.MoviesDomain
import com.example.movieapp.domain.repository.movies.MoviesRepository
import com.example.movieapp.util.Resources

class GetMoviesUseCaseImpl(private val repository: MoviesRepository) : GetMoviesUseCase {

    override suspend fun getPopularMovies(
        page: Int,
        action: (message: String) -> Unit,
    ): List<MoviesDomain>? {
        return when (val response =
            repository.getPopularMovies(page)) {
            is Resources.Success -> {
                response.data
            }
            is Resources.Error -> {
                action.invoke(response.message)
                null
            }
        }
    }

    override suspend fun getTopRatedMovies(
        page: Int,
        action: (message: String) -> Unit,
    ): List<MoviesDomain>? {
        return when (val response =
            repository.getTopRatedMovies(page)) {
            is Resources.Success -> {
                response.data
            }
            is Resources.Error -> {
                action.invoke(response.message)
                null
            }
        }
    }
}