package com.example.movieapp.data.repository.movies

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.example.movieapp.data.datasource.paging_source.PopularMoviesPagingSource
import com.example.movieapp.data.datasource.paging_source.TopRatedMoviesPagingSource
import com.example.movieapp.data.datasource.remote.MoviesApiService
import com.example.movieapp.data.mapper.DataMapperImpl
import com.example.movieapp.domain.model.MoviesDomain
import com.example.movieapp.domain.repository.movies.MoviesRepository
import com.example.movieapp.util.Resources
import kotlinx.coroutines.flow.Flow

class MoviesRepositoryImpl(
    private val moviesApi: MoviesApiService,
    private val dataMapper: DataMapperImpl,
) : MoviesRepository {

    override suspend fun getTopRatedMovies(
        page: Int,
        pagingConfig: PagingConfig,
    ): Resources<Flow<PagingData<MoviesDomain>>> {
        return try {
            val response = moviesApi.getTopRatedMoves(page)
            if (response.isSuccessful) {
                Resources.Success(
                    Pager(
                        config = pagingConfig,
                        pagingSourceFactory = {
                            TopRatedMoviesPagingSource(
                                apiService = moviesApi,
                                dataMapper = dataMapper
                            )
                        }
                    ).flow
                )
            } else {
                Resources.Error(response.message())
            }
        } catch (e: Exception) {
            Resources.Error(e.message!!)
        }
    }

    override suspend fun getPopularMovies(
        page: Int,
        pagingConfig: PagingConfig,
    ): Resources<Flow<PagingData<MoviesDomain>>> {
        return try {
            val response = moviesApi.getPopularMovies(page)
            if (response.isSuccessful) {
                Resources.Success(
                    Pager(
                        config = pagingConfig,
                        pagingSourceFactory = {
                            PopularMoviesPagingSource(
                                apiService = moviesApi,
                                dataMapper = dataMapper
                            )
                        }
                    ).flow
                )
            } else {
                Resources.Error(response.message())
            }
        } catch (e: Exception) {
            Resources.Error(e.message!!)
        }
    }
}