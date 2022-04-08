package com.example.movieapp.data.repository.favourite_movies

import com.example.movieapp.data.datasource.local.dao.MoviesDao
import com.example.movieapp.data.mapper.DataMapperImpl
import com.example.movieapp.domain.model.MoviesDomain
import com.example.movieapp.domain.repository.favourite_movies.FavouriteMoviesRepository

class FavouriteMoviesRepositoryImpl(
    private val dataMapper: DataMapperImpl,
    private val dao: MoviesDao,
) : FavouriteMoviesRepository {

    override suspend fun getFavouriteMovies(): List<MoviesDomain> {
        return dataMapper.fromEntity(dao.getAllMovies())
    }
}