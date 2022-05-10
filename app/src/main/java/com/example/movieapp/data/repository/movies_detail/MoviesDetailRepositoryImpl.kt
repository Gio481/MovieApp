package com.example.movieapp.data.repository.movies_detail

import com.example.movieapp.data.datasource.local.dao.MoviesDao
import com.example.movieapp.data.datasource.local.entity.MoviesEntity
import com.example.movieapp.data.mapper.EntityMapper
import com.example.movieapp.domain.model.MoviesDomain
import com.example.movieapp.domain.repository.movies_detail.MoviesDetailRepository

class MoviesDetailRepositoryImpl(
    private val moviesDao: MoviesDao,
    private val entityMapper: EntityMapper<MoviesEntity, MoviesDomain>,
) : MoviesDetailRepository {

    override suspend fun getAllMoviesPoster(): List<String> {
        return moviesDao.getAllMoviesPoster()
    }

    override suspend fun insertMovie(movie: MoviesDomain) {
        return moviesDao.insert(entityMapper.toEntity(movie))
    }

    override suspend fun deleteMovie(posterPath: String) = moviesDao.deleteMovie(posterPath)
}