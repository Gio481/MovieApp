package com.example.movieapp.data.repository.movies

import androidx.lifecycle.LiveData
import androidx.lifecycle.map
import androidx.paging.*
import com.example.movieapp.data.datasource.paging_source.PopularMoviesPagingSource
import com.example.movieapp.data.datasource.paging_source.TopRatedMoviesPagingSource
import com.example.movieapp.data.datasource.remote.MoviesApiService
import com.example.movieapp.data.mapper.DataMapperImpl
import com.example.movieapp.domain.model.MoviesDomain
import com.example.movieapp.domain.repository.movies.MoviesRepository
import com.example.movieapp.util.Response

class MoviesRepositoryImpl(
    private val moviesApi: MoviesApiService,
    private val dataMapper: DataMapperImpl,
) : MoviesRepository {

    override suspend fun getTopRatedMovies(
        page: Int,
        pagingConfig: PagingConfig,
    ): Response<LiveData<PagingData<MoviesDomain>>> {
        return try {
            val response = moviesApi.getTopRatedMoves(page)
            if (response.isSuccessful) {
                return Response.Success(
                    Pager(
                        config = pagingConfig,
                        pagingSourceFactory = {
                            TopRatedMoviesPagingSource(
                                apiService = moviesApi,
                            )
                        }
                    ).liveData.map {
                        it.map { dto ->
                            dataMapper.dtoToDomain(dto)
                        }
                    }
                )
            } else {
                Response.Error(response.message())
            }
        } catch (e: Exception) {
            Response.Error(e.message!!)
        }
    }


    override suspend fun getPopularMovies(
        page: Int,
        pagingConfig: PagingConfig,
    ): Response<LiveData<PagingData<MoviesDomain>>> {
        return try {
            val response = moviesApi.getPopularMovies(page)
            if (response.isSuccessful) {
                Response.Success(
                    Pager(
                        config = pagingConfig,
                        pagingSourceFactory = {
                            PopularMoviesPagingSource(
                                apiService = moviesApi,
                            )
                        }
                    ).liveData.map {
                        it.map { dto ->
                            dataMapper.dtoToDomain(dto)
                        }
                    }
                )
            } else {
                Response.Error(response.message())
            }
        } catch (e: Exception) {
            Response.Error(e.message!!)
        }
    }
}