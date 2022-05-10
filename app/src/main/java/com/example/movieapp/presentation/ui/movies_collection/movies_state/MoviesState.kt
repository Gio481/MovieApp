package com.example.movieapp.presentation.ui.movies_collection.movies_state

sealed class MoviesState {
    object TopRatedMoviesState : MoviesState()
    object PopularMoviesState : MoviesState()
    object FavouriteMoviesState : MoviesState()
}