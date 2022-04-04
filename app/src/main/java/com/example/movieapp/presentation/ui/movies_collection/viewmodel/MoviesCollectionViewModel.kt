package com.example.movieapp.presentation.ui.movies_collection.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.paging.PagingData
import com.example.movieapp.domain.interactor.usecases.get_favourite_movies.GetFavouriteMoviesUseCase
import com.example.movieapp.domain.interactor.usecases.get_movies.GetMoviesUseCase
import com.example.movieapp.domain.model.MoviesDomain
import com.example.movieapp.presentation.ui.movies_collection.movies_state.MoviesState

class MoviesCollectionViewModel(
    private val moviesUseCase: GetMoviesUseCase,
    private val favMoviesUseCase: GetFavouriteMoviesUseCase,
) : ViewModel() {

    private val _errorLiveData: MutableLiveData<String> = MutableLiveData()
    val errorLiveData: LiveData<String> = _errorLiveData

    var isSubmittedEmptyData = true
    var isShownInternetAvailability = false
    var getMovieState: MoviesState = MoviesState.TopRatedMoviesState

    suspend fun getMovies(moviesState: MoviesState): LiveData<PagingData<MoviesDomain>>? {
        return when (moviesState) {
            is MoviesState.FavouriteMoviesState -> {
                getMovieState = MoviesState.FavouriteMoviesState
                favMoviesUseCase.getFavouriteMovies()
            }
            is MoviesState.PopularMoviesState -> {
                getMovieState = MoviesState.PopularMoviesState
                moviesUseCase.getPopularMovies {
                    _errorLiveData.postValue(it)
                }
            }
            is MoviesState.TopRatedMoviesState -> {
                getMovieState = MoviesState.TopRatedMoviesState
                moviesUseCase.getTopRatedMovies {
                    _errorLiveData.postValue(it)
                }
            }
        }
    }
}