package com.example.movieapp.presentation.ui.movies.movies_state

sealed class MoviesState {
    object TopRatedMovies : MoviesState()
    object PopularMoviesState : MoviesState()
}