package com.example.movieapp.data.repository.favourite_movies

import androidx.lifecycle.LiveData
import androidx.lifecycle.map
import androidx.paging.*
import com.example.movieapp.data.datasource.local.dao.MoviesDao
import com.example.movieapp.data.mapper.DataMapperImpl
import com.example.movieapp.domain.model.MoviesDomain
import com.example.movieapp.domain.repository.favourite_movies.FavouriteMoviesRepository
import com.example.movieapp.util.Constants.MOVIES_PAGE_SIZE

class FavouriteMoviesRepositoryImpl(
    private val dataMapper: DataMapperImpl,
    private val dao: MoviesDao,
) : FavouriteMoviesRepository {

    override fun getFavouriteMovies(): LiveData<PagingData<MoviesDomain>> {
        return Pager(
            PagingConfig(
                pageSize = MOVIES_PAGE_SIZE,
                enablePlaceholders = false
            )
        ) {
            dao.getAllMovies()
        }.liveData.map { pagingData ->
            pagingData.map { movieEntity ->
                dataMapper.fromEntity(movieEntity)
            }
        }
    }
}