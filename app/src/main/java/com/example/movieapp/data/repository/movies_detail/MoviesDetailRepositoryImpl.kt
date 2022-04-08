package com.example.movieapp.data.repository.movies_detail

import com.example.movieapp.data.datasource.local.dao.MoviesDao
import com.example.movieapp.data.mapper.DataMapperImpl
import com.example.movieapp.domain.model.MoviesDomain
import com.example.movieapp.domain.repository.movies_detail.MoviesDetailRepository

class MoviesDetailRepositoryImpl(
    private val moviesDao: MoviesDao,
    private val dataMapper: DataMapperImpl,
) : MoviesDetailRepository {

    override suspend fun getAllMoviesPoster(): List<String> {
        return moviesDao.getAllMoviesPoster()
    }

    override suspend fun insertMovie(movie: MoviesDomain) {
        return moviesDao.insert(dataMapper.toEntity(movie))
    }

    override suspend fun deleteMovie(posterPath: String) = moviesDao.deleteMovie(posterPath)
}