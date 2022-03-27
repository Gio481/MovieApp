package com.example.movieapp.presentation.ui.movies.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import com.example.movieapp.domain.interactor.usecases.get_movies.GetMoviesUseCase
import com.example.movieapp.domain.model.MoviesDomain
import com.example.movieapp.presentation.ui.movies.movies_state.MoviesState
import com.example.movieapp.util.Resources
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch

class MoviesViewModel(private val useCase: GetMoviesUseCase) : ViewModel() {

    private val _moviesLiveData: MutableLiveData<Flow<PagingData<MoviesDomain>>> = MutableLiveData()
    val moviesLiveData: LiveData<Flow<PagingData<MoviesDomain>>> get() = _moviesLiveData

    private val _errorLiveData: MutableLiveData<String> = MutableLiveData()
    val errorLiveData: LiveData<String> = _errorLiveData


    init {
        viewModelScope.launch {
            getMovies(useCase.getTopRatedMovies())
        }
    }

    private fun getMovies(response: Resources<Flow<PagingData<MoviesDomain>>>) {
        when (response) {
            is Resources.Success -> _moviesLiveData.postValue(response.data!!)
            is Resources.Error -> _errorLiveData.postValue(response.message)
        }
    }

    fun getMoviesState(moviesState: MoviesState) {
        viewModelScope.launch(Dispatchers.IO) {
            when (moviesState) {
                is MoviesState.TopRatedMovies -> {
                    getMovies(useCase.getTopRatedMovies())

                }
                is MoviesState.PopularMoviesState -> {
                    getMovies(useCase.getPopularMovies())

                }
            }
        }
    }
}