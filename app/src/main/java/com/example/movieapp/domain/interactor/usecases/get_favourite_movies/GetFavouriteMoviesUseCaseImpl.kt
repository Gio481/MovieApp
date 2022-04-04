package com.example.movieapp.domain.interactor.usecases.get_favourite_movies

import androidx.lifecycle.LiveData
import androidx.paging.PagingData
import com.example.movieapp.domain.model.MoviesDomain
import com.example.movieapp.domain.repository.favourite_movies.FavouriteMoviesRepository
import kotlinx.coroutines.flow.Flow

class GetFavouriteMoviesUseCaseImpl(private val repository: FavouriteMoviesRepository) :
    GetFavouriteMoviesUseCase {

    override fun getFavouriteMovies(): LiveData<PagingData<MoviesDomain>> {
        return repository.getFavouriteMovies()
    }
}