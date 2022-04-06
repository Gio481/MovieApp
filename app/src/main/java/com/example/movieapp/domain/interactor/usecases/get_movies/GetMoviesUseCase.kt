package com.example.movieapp.domain.interactor.usecases.get_movies

import com.example.movieapp.domain.model.MoviesDomain

interface GetMoviesUseCase {
    suspend fun getPopularMovies(action: (message: String) -> Unit): List<MoviesDomain>?
    suspend fun getTopRatedMovies(action: (message: String) -> Unit): List<MoviesDomain>?
}