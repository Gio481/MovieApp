package com.example.movieapp.domain.repository.movies

import com.example.movieapp.domain.model.MoviesDomain
import com.example.movieapp.util.Resources

interface MoviesRepository {
    suspend fun getTopRatedMovies(page: Int): Resources<List<MoviesDomain>>
    suspend fun getPopularMovies(page: Int): Resources<List<MoviesDomain>>
}