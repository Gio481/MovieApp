package com.example.movieapp.presentation.ui.movie_details.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.movieapp.domain.interactor.usecases.get_movies_detail.GetMoviesDetailUseCase
import com.example.movieapp.domain.model.FavouriteMoviesDomain
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MovieDetailsViewModel(private val useCase: GetMoviesDetailUseCase) : ViewModel() {

    fun insertMovies(movieId: Int, poster: String, releaseDate: String, rating: Double) {
        viewModelScope.launch(Dispatchers.IO) {
            val favouriteMovie =
                FavouriteMoviesDomain(
                    id = movieId,
                    poster = poster,
                    releaseDate = releaseDate,
                    rating = rating
                )
            useCase.insertMovie(favouriteMovie)
        }
    }
}