package com.example.movieapp.data.repository.movies_detail

import com.example.movieapp.data.datasource.local.dao.MoviesDao
import com.example.movieapp.data.mapper.DataMapperImpl
import com.example.movieapp.domain.model.FavouriteMoviesDomain
import com.example.movieapp.domain.repository.movies_detail.MoviesDetailRepository

class MoviesDetailRepositoryImpl(
    private val moviesDao: MoviesDao,
    private val dataMapper: DataMapperImpl,
) : MoviesDetailRepository {

    override suspend fun getAllMoviesID(): List<Int> {
        return moviesDao.getAllMoviesID()
    }

    override suspend fun insertMovie(movie: FavouriteMoviesDomain) {
        return moviesDao.insert(dataMapper.toEntity(movie))
    }

    override suspend fun deleteMovie(moviesId: Int) = moviesDao.deleteMovie(moviesId)
}