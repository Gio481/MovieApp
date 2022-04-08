package com.example.movieapp.data.repository.favourite_movies

import com.example.movieapp.data.datasource.local.dao.MoviesDao
import com.example.movieapp.data.datasource.local.entity.MoviesEntity
import com.example.movieapp.data.mapper.EntityMapper
import com.example.movieapp.domain.model.MoviesDomain
import com.example.movieapp.domain.repository.favourite_movies.FavouriteMoviesRepository

class FavouriteMoviesRepositoryImpl(
    private val entityMapper: EntityMapper<MoviesEntity, MoviesDomain>,
    private val dao: MoviesDao,
) : FavouriteMoviesRepository {

    override suspend fun getFavouriteMovies(): List<MoviesDomain> {
        return entityMapper.fromEntity(dao.getAllMovies())
    }
}