package com.example.movieapp.domain.repository.movies

import androidx.lifecycle.LiveData
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.example.movieapp.domain.model.MoviesDomain
import com.example.movieapp.util.Response
import kotlinx.coroutines.flow.Flow

interface MoviesRepository {
    suspend fun getTopRatedMovies(
        page: Int,
        pagingConfig: PagingConfig,
    ): Response<LiveData<PagingData<MoviesDomain>>>

    suspend fun getPopularMovies(
        page: Int,
        pagingConfig: PagingConfig,
    ): Response<LiveData<PagingData<MoviesDomain>>>
}