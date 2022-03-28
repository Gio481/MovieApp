package com.example.movieapp.presentation.ui.favourite_movies.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.movieapp.domain.interactor.usecases.get_favourite_movies.GetFavouriteMoviesUseCase
import com.example.movieapp.domain.model.MoviesDomain
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class FavouriteMoviesViewModel(private val useCase: GetFavouriteMoviesUseCase) : ViewModel() {

    private val _favouriteMoviesLiveData: MutableLiveData<List<MoviesDomain>> =
        MutableLiveData()
    val favouriteMoviesLiveData: LiveData<List<MoviesDomain>> = _favouriteMoviesLiveData

    init {
        favouriteMovies()
    }

    private fun favouriteMovies() {
        viewModelScope.launch(Dispatchers.IO) {
            _favouriteMoviesLiveData.postValue(useCase.getFavouriteMovies())
        }
    }
}