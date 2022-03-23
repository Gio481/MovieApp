package com.example.movieapp.presentation.ui.movies_collection

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import com.example.movieapp.domain.interactor.usecases.get_movies.GetMoviesUseCase
import com.example.movieapp.domain.model.FavouriteMoviesDomain
import com.example.movieapp.domain.model.MoviesDomain
import com.example.movieapp.util.Resources
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MoviesCollectionViewModel(private val useCase: GetMoviesUseCase) : ViewModel() {

    private lateinit var _moviesLiveData: LiveData<PagingData<MoviesDomain>>
    val moviesLiveData: LiveData<PagingData<MoviesDomain>> get() = _moviesLiveData

    private val _errorLiveData: MutableLiveData<String> = MutableLiveData()
    val errorLiveData: LiveData<String> = _errorLiveData

    private val _favouriteMoviesLiveData: MutableLiveData<List<FavouriteMoviesDomain>> =
        MutableLiveData()
    val favouriteMoviesLiveData: LiveData<List<FavouriteMoviesDomain>> = _favouriteMoviesLiveData

    init {
        viewModelScope.launch {
            getMovies(useCase.getTopRatedMovies())
        }
    }

    private fun getMovies(response: Resources<LiveData<PagingData<MoviesDomain>>>) {
        when (response) {
            is Resources.Success -> _moviesLiveData = response.data!!
            is Resources.Error -> _errorLiveData.postValue(response.message)
        }
    }

    fun getMoviesState(moviesState: MoviesState) {
        viewModelScope.launch(Dispatchers.IO) {
            when (moviesState) {
                is MoviesState.TopRatedMovies -> getMovies(useCase.getTopRatedMovies())
                is MoviesState.PopularMoviesState -> getMovies(useCase.getPopularMovies())
                is MoviesState.FavouriteMoviesState -> _favouriteMoviesLiveData.postValue(useCase.getFavouriteMovies())
            }
        }
    }
}