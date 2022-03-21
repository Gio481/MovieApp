package com.example.movieapp.domain.interactor.usecases.get_movies

interface GetMoviesUseCase {
    suspend fun getPopularMovies(page:Int)
    suspend fun getTopRatedMovies(page:Int)
    suspend fun getFavouriteMovies()
}