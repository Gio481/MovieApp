package com.example.movieapp.presentation.ui.movies_collection.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.movieapp.domain.interactor.usecases.get_favourite_movies.GetFavouriteMoviesUseCase
import com.example.movieapp.domain.interactor.usecases.get_movies.GetMoviesUseCase
import com.example.movieapp.domain.model.MoviesDomain
import com.example.movieapp.presentation.ui.movies_collection.movies_state.MoviesState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MoviesCollectionViewModel(
    private val moviesUseCase: GetMoviesUseCase,
    private val favMoviesUseCase: GetFavouriteMoviesUseCase,
) : ViewModel() {

    private val _errorLiveData: MutableLiveData<String?> = MutableLiveData()
    val errorLiveData: LiveData<String?> = _errorLiveData

    private val _moviesLiveData: MutableLiveData<List<MoviesDomain>?> = MutableLiveData()
    val moviesLiveData: LiveData<List<MoviesDomain>?> = _moviesLiveData

    var getMovieState: MoviesState = MoviesState.TopRatedMoviesState

    var isFirstChecked = false

    init {
        getMovies(MoviesState.TopRatedMoviesState)
    }

    fun getMovies(moviesState: MoviesState) {
        viewModelScope.launch(Dispatchers.IO) {
            when (moviesState) {
                is MoviesState.FavouriteMoviesState -> {
                    _moviesLiveData.postValue(favMoviesUseCase.getFavouriteMovies())
                }
                is MoviesState.PopularMoviesState -> {
                    _moviesLiveData.postValue(moviesUseCase.getPopularMovies {
                        _errorLiveData.postValue(it)
                    })
                }
                is MoviesState.TopRatedMoviesState -> {
                    _moviesLiveData.postValue(moviesUseCase.getTopRatedMovies {
                        _errorLiveData.postValue(it)
                    })
                }
            }
        }
    }
}