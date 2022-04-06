package com.example.movieapp.data.repository.movies

import com.example.movieapp.data.datasource.remote.MoviesApiService
import com.example.movieapp.data.mapper.DataMapperImpl
import com.example.movieapp.domain.model.MoviesDomain
import com.example.movieapp.domain.repository.movies.MoviesRepository
import com.example.movieapp.util.Resources
import com.example.movieapp.util.dataFetcher

class MoviesRepositoryImpl(
    private val moviesApi: MoviesApiService,
    private val dataMapper: DataMapperImpl,
) : MoviesRepository {

    override suspend fun getTopRatedMovies(
        page: Int,
    ): Resources<List<MoviesDomain>> {
        return dataFetcher(dataMapper) {
            moviesApi.getTopRatedMoves(page)
        }
    }


    override suspend fun getPopularMovies(
        page: Int,
    ): Resources<List<MoviesDomain>> {
        return dataFetcher(dataMapper) {
            moviesApi.getPopularMovies(page)
        }
    }
}