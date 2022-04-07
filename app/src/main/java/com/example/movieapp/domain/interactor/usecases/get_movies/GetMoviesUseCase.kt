package com.example.movieapp.domain.interactor.usecases.get_movies

import com.example.movieapp.domain.model.MoviesDomain

interface GetMoviesUseCase {
    suspend fun getPopularMovies(page: Int, action: (message: String) -> Unit): List<MoviesDomain>?
    suspend fun getTopRatedMovies(page: Int, action: (message: String) -> Unit): List<MoviesDomain>?
}