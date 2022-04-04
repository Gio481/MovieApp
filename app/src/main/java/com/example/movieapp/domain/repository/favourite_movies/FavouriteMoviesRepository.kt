package com.example.movieapp.domain.repository.favourite_movies

import androidx.lifecycle.LiveData
import androidx.paging.Pager
import androidx.paging.PagingData
import androidx.paging.PagingSource
import com.example.movieapp.data.datasource.local.entity.MoviesEntity
import com.example.movieapp.domain.model.MoviesDomain
import kotlinx.coroutines.flow.Flow

interface FavouriteMoviesRepository {
     fun getFavouriteMovies(): LiveData<PagingData<MoviesDomain>>
}