package com.example.movieapp.data.repository.movies

import com.example.movieapp.data.datasource.local.dao.MoviesDao
import com.example.movieapp.data.datasource.remote.MoviesApiService
import com.example.movieapp.data.mapper.DataMapperClass
import com.example.movieapp.domain.model.FavouriteMoviesDomain
import com.example.movieapp.domain.model.MoviesDomain
import com.example.movieapp.domain.repository.movies.MoviesRepository
import com.example.movieapp.util.Resources

class MoviesRepositoryImpl(
    private val moviesApi: MoviesApiService,
    private val moviesDao: MoviesDao,
    private val dataMapper: DataMapperClass
) : MoviesRepository {

    override suspend fun getTopRatedMovies(page: Int): Resources<List<MoviesDomain>> {
        return try {
            val response = moviesApi.getTopRatedMoves(page)
            if (response.isSuccessful) {
                Resources.Success(dataMapper.moviesDomain(response.body()!!))
            } else {
                Resources.Error(response.message())
            }
        } catch (e: Exception) {
            Resources.Error(e.message!!)
        }
    }

    override suspend fun getPopularMovies(page: Int): Resources<List<MoviesDomain>> {
        return try {
            val response = moviesApi.getPopularMovies(page)
            if (response.isSuccessful) {
                Resources.Success(dataMapper.moviesDomain(response.body()!!))
            } else {
                Resources.Error(response.message())
            }
        } catch (e: Exception) {
            Resources.Error(e.message!!)
        }
    }

    override suspend fun getFavouriteMovies(): List<FavouriteMoviesDomain> {
        return dataMapper.fromEntity(moviesDao.getAllMovies())
    }

}