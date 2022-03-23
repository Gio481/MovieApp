package com.example.movieapp.domain.interactor.usecases.get_movies

import androidx.lifecycle.LiveData
import androidx.paging.PagingData
import com.example.movieapp.domain.model.FavouriteMoviesDomain
import com.example.movieapp.domain.model.MoviesDomain
import com.example.movieapp.util.Resources

interface GetMoviesUseCase {
    suspend fun getPopularMovies(): Resources<LiveData<PagingData<MoviesDomain>>>
    suspend fun getTopRatedMovies(): Resources<LiveData<PagingData<MoviesDomain>>>
    suspend fun getFavouriteMovies(): List<FavouriteMoviesDomain>
}