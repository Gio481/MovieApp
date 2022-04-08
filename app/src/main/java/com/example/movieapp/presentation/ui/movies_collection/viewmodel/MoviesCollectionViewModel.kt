package com.example.movieapp.presentation.ui.movies_collection.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.movieapp.FlavorVersion
import com.example.movieapp.domain.interactor.usecases.get_favourite_movies.GetFavouriteMoviesUseCase
import com.example.movieapp.domain.interactor.usecases.get_movies.GetMoviesUseCase
import com.example.movieapp.domain.model.MoviesDomain
import com.example.movieapp.presentation.ui.movies_collection.movies_state.MoviesState
import com.example.movieapp.util.Constants.STARTING_PAGE_INDEX
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MoviesCollectionViewModel(
    private val moviesUseCase: GetMoviesUseCase,
    private val favMoviesUseCase: GetFavouriteMoviesUseCase,
    private val flavorVersion: FlavorVersion,
) : ViewModel() {

    val category = flavorVersion.category

    private val _errorLiveData: MutableLiveData<String?> = MutableLiveData()
    val errorLiveData: LiveData<String?> = _errorLiveData

    private val _moviesLiveData: MutableLiveData<List<MoviesDomain>?> = MutableLiveData()
    val moviesLiveData: LiveData<List<MoviesDomain>?> = _moviesLiveData

    private val topRatedMoviesList = mutableListOf<MoviesDomain>()
    private val popularMoviesList = mutableListOf<MoviesDomain>()

    var getMovieState: MoviesState = MoviesState.TopRatedMoviesState
    var internetConnection: Boolean? = null
    var isFirstChecked = false
    var page = STARTING_PAGE_INDEX

    fun getMovies(moviesState: MoviesState) {
        viewModelScope.launch(Dispatchers.IO) {
            when (moviesState) {
                is MoviesState.FavouriteMoviesState -> {
                    _moviesLiveData.postValue(favMoviesUseCase.getFavouriteMovies())
                }
                is MoviesState.PopularMoviesState -> {
                    getPopularMovies()
                }
                is MoviesState.TopRatedMoviesState -> {
                    getTopRatedMovies()
                }
            }
        }
    }

    private suspend fun getTopRatedMovies() {
        val response = moviesUseCase.getTopRatedMovies(page) {
            _errorLiveData.postValue(it)
        }
        page++
        response?.let { topRatedMoviesList.addAll(it) }
        _moviesLiveData.postValue(topRatedMoviesList)
    }

    private suspend fun getPopularMovies() {
        val response = moviesUseCase.getPopularMovies(page) {
            _errorLiveData.postValue(it)
        }
        page++
        response?.let { popularMoviesList.addAll(it) }
        _moviesLiveData.postValue(popularMoviesList)
    }

    fun clearMoviesListAndLiveData() {
        _moviesLiveData.postValue(null)
        popularMoviesList.clear()
        topRatedMoviesList.clear()
        page = STARTING_PAGE_INDEX
    }
}