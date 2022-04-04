package com.example.movieapp.domain.interactor.usecases.get_favourite_movies

import androidx.lifecycle.LiveData
import androidx.paging.PagingData
import com.example.movieapp.data.datasource.local.entity.MoviesEntity
import com.example.movieapp.domain.model.MoviesDomain
import kotlinx.coroutines.flow.Flow

interface GetFavouriteMoviesUseCase {
     fun getFavouriteMovies(): LiveData<PagingData<MoviesDomain>>

}