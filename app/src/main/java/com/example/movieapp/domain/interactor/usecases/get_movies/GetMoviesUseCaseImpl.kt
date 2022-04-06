package com.example.movieapp.domain.interactor.usecases.get_movies

import com.example.movieapp.domain.model.MoviesDomain
import com.example.movieapp.domain.repository.movies.MoviesRepository
import com.example.movieapp.util.Resources

class GetMoviesUseCaseImpl(private val repository: MoviesRepository) : GetMoviesUseCase {

    private var popularMoviesPage = 1
    private val popularMoviesList = mutableListOf<MoviesDomain>()

    override suspend fun getPopularMovies(action: (message: String) -> Unit): MutableList<MoviesDomain>? {
        return when (val response =
            repository.getPopularMovies(popularMoviesPage)) {
            is Resources.Success -> {
                popularMoviesPage++
                popularMoviesList.addAll(response.data!!)
                popularMoviesList
            }
            is Resources.Error -> {
                action.invoke(response.message)
                null
            }
        }
    }

    private var topRatedPages = 1
    private val topRatedMoviesList = mutableListOf<MoviesDomain>()

    override suspend fun getTopRatedMovies(action: (message: String) -> Unit): MutableList<MoviesDomain>? {
        return when (val response =
            repository.getTopRatedMovies(topRatedPages)) {
            is Resources.Success -> {
                topRatedPages++
                topRatedMoviesList.addAll(response.data!!)
                topRatedMoviesList
            }
            is Resources.Error -> {
                action.invoke(response.message)
                null
            }
        }
    }
}