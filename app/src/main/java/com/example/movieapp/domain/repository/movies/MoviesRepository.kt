package com.example.movieapp.domain.repository.movies

import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.example.movieapp.domain.model.FavouriteMoviesDomain
import com.example.movieapp.domain.model.MoviesDomain
import com.example.movieapp.util.Resources
import kotlinx.coroutines.flow.Flow

interface MoviesRepository {
    suspend fun getTopRatedMovies(page: Int, pagingConfig: PagingConfig): Resources<Flow<PagingData<MoviesDomain>>>
    suspend fun getPopularMovies(page: Int, pagingConfig: PagingConfig): Resources<Flow<PagingData<MoviesDomain>>>
    suspend fun getFavouriteMovies(): List<FavouriteMoviesDomain>
}