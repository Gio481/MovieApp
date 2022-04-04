package com.example.movieapp.domain.interactor.usecases.get_movies

import androidx.lifecycle.LiveData
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.example.movieapp.domain.model.MoviesDomain
import com.example.movieapp.domain.repository.movies.MoviesRepository
import com.example.movieapp.util.Constants.MOVIES_PAGE_SIZE
import com.example.movieapp.util.Constants.STARTING_PAGE_INDEX
import com.example.movieapp.util.Response

class GetMoviesUseCaseImpl(private val repository: MoviesRepository) : GetMoviesUseCase {

    override suspend fun getPopularMovies(action: (message: String) -> Unit): LiveData<PagingData<MoviesDomain>>? {
        return when (val response =
            repository.getPopularMovies(page = STARTING_PAGE_INDEX, pagingConfig())) {
            is Response.Success -> response.data!!
            is Response.Error -> {
                action.invoke(response.message)
                null
            }
        }
    }

    override suspend fun getTopRatedMovies(action: (message: String) -> Unit): LiveData<PagingData<MoviesDomain>>? {
        return when (val response =
            repository.getTopRatedMovies(page = STARTING_PAGE_INDEX, pagingConfig())) {
            is Response.Success -> {
                response.data!!
            }
            is Response.Error -> {
                action.invoke(response.message)
                null
            }
        }
    }

    override fun pagingConfig(): PagingConfig {
        return PagingConfig(pageSize = MOVIES_PAGE_SIZE, enablePlaceholders = false)
    }
}