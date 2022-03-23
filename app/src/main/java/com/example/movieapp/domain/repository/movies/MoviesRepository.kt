package com.example.movieapp.domain.repository.movies

import androidx.lifecycle.LiveData
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.example.movieapp.domain.model.FavouriteMoviesDomain
import com.example.movieapp.domain.model.MoviesDomain
import com.example.movieapp.util.Resources

interface MoviesRepository {
    suspend fun getTopRatedMovies(
        page: Int,
        pagingConfig: PagingConfig
    ): Resources<LiveData<PagingData<MoviesDomain>>>

    suspend fun getPopularMovies(
        page: Int,
        pagingConfig: PagingConfig
    ): Resources<LiveData<PagingData<MoviesDomain>>>

    suspend fun getFavouriteMovies(): List<FavouriteMoviesDomain>
}