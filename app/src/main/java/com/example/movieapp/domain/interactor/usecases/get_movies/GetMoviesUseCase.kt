package com.example.movieapp.domain.interactor.usecases.get_movies

import androidx.paging.PagingData
import com.example.movieapp.domain.model.FavouriteMoviesDomain
import com.example.movieapp.domain.model.MoviesDomain
import com.example.movieapp.util.Resources
import kotlinx.coroutines.flow.Flow

interface GetMoviesUseCase {
    suspend fun getPopularMovies(): Resources<Flow<PagingData<MoviesDomain>>>
    suspend fun getTopRatedMovies(): Resources<Flow<PagingData<MoviesDomain>>>
    suspend fun getFavouriteMovies(): List<FavouriteMoviesDomain>
}