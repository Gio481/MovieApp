package com.example.movieapp.presentation.ui.favourite_movies.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.movieapp.domain.interactor.usecases.get_movies.GetMoviesUseCase
import com.example.movieapp.domain.model.FavouriteMoviesDomain
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class FavouriteMoviesViewModel(private val useCase: GetMoviesUseCase) : ViewModel() {

    private val _favouriteMoviesLiveData: MutableLiveData<List<FavouriteMoviesDomain>> =
        MutableLiveData()
    val favouriteMoviesLiveData: LiveData<List<FavouriteMoviesDomain>> = _favouriteMoviesLiveData

    init {
        favouriteMovies()
    }

    private fun favouriteMovies() {
        viewModelScope.launch(Dispatchers.IO) {
            _favouriteMoviesLiveData.postValue(useCase.getFavouriteMovies())
        }
    }
}