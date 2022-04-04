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

    private val _actionButtonBackgroundLiveData: MutableLiveData<Int> = MutableLiveData()
    val actionButtonBackgroundLiveData: LiveData<Int> get() = _actionButtonBackgroundLiveData

    suspend fun isMovieSaved(posterPath: String): Boolean =
        posterPath in useCase.getAllMoviesPosterPath()

    fun determineInsertOrDelete(
        posterPath: String, moviesDomain: MoviesDomain,
    ) {
        viewModelScope.launch(Dispatchers.IO) {
            if (isMovieSaved(posterPath)) {
                deleteMovie(posterPath)
            } else {
                insertMovies(moviesDomain)
            }
        }
    }

    private suspend fun insertMovies(moviesDomain: MoviesDomain) {
        useCase.insertMovie(moviesDomain)
    }

    private suspend fun deleteMovie(posterPath: String) {
        useCase.deleteMovie(posterPath)
    }

    fun determineActionButtonBackground(posterPath: String) {
        viewModelScope.launch {
            if (isMovieSaved(posterPath)) {
                _actionButtonBackgroundLiveData.postValue(R.drawable.ic_delete)
            } else {
                _actionButtonBackgroundLiveData.postValue(R.drawable.ic_favourite)
            }
        }
    }
}