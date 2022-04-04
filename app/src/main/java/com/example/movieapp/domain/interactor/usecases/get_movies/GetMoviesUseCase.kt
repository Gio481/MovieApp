package com.example.movieapp.domain.interactor.usecases.get_movies

import androidx.lifecycle.LiveData
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.example.movieapp.domain.model.MoviesDomain

interface GetMoviesUseCase {
    suspend fun getPopularMovies(action: (message: String) -> Unit): LiveData<PagingData<MoviesDomain>>?
    suspend fun getTopRatedMovies(action: (message: String) -> Unit): LiveData<PagingData<MoviesDomain>>?
    fun pagingConfig(): PagingConfig
}