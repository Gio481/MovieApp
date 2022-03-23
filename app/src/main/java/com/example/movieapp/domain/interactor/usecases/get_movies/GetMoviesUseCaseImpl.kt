package com.example.movieapp.domain.interactor.usecases.get_movies

import androidx.lifecycle.LiveData
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.example.movieapp.domain.model.MoviesDomain
import com.example.movieapp.domain.repository.movies.MoviesRepository
import com.example.movieapp.util.Constants.MOVIES_PAGE_SIZE
import com.example.movieapp.util.Constants.STARTING_PAGE_INDEX
import com.example.movieapp.util.Resources
import kotlinx.coroutines.flow.Flow

class GetMoviesUseCaseImpl(private val repository: MoviesRepository) : GetMoviesUseCase {
    override suspend fun getPopularMovies(): Resources<LiveData<PagingData<MoviesDomain>>> {
        return repository.getPopularMovies(page = STARTING_PAGE_INDEX, pagingConfig())
    }

    override suspend fun getTopRatedMovies(): Resources<LiveData<PagingData<MoviesDomain>>> {
        return repository.getTopRatedMovies(page = STARTING_PAGE_INDEX, pagingConfig())
    }

    override suspend fun getFavouriteMovies() {
        repository.getFavouriteMovies()
    }

    private fun pagingConfig(): PagingConfig {
        return PagingConfig(pageSize = MOVIES_PAGE_SIZE, enablePlaceholders = false)
    }
}