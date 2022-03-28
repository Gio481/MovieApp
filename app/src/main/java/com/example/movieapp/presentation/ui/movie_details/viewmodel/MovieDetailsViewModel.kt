package com.example.movieapp.presentation.ui.movie_details.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.movieapp.R
import com.example.movieapp.domain.interactor.usecases.get_movies_detail.GetMoviesDetailUseCase
import com.example.movieapp.domain.model.MoviesDomain
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MovieDetailsViewModel(private val useCase: GetMoviesDetailUseCase) : ViewModel() {

    private val _backgroundLiveData: MutableLiveData<Int> = MutableLiveData()
    val backgroundLiveData: LiveData<Int> get() = _backgroundLiveData

    suspend fun checkSavedMovieId(movieId: Int): Boolean = movieId in useCase.getAllMoviesID()

    fun determineOperation(
        movieId: Int,
        moviesDomain: MoviesDomain,
    ) {
        viewModelScope.launch {
            if (checkSavedMovieId(movieId)) {
                deleteMovie(movieId)
            } else {
                insertMovies(moviesDomain)
            }
        }
    }

    private suspend fun insertMovies(moviesDomain: MoviesDomain) {
        viewModelScope.launch(Dispatchers.IO) {
            useCase.insertMovie(moviesDomain)
        }
    }

    private suspend fun deleteMovie(movieId: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            useCase.deleteMovie(movieId)
        }
    }

    fun determineBackground(movieId: Int) {
        viewModelScope.launch {
            if (checkSavedMovieId(movieId)) {
                _backgroundLiveData.postValue(R.drawable.ic_delete)
            } else {
                _backgroundLiveData.postValue(R.drawable.ic_favourite)
            }
        }
    }
}