package com.example.movieapp.presentation.ui.movies_collection

sealed class MoviesState {
    object TopRatedMovies : MoviesState()
    object PopularMoviesState : MoviesState()
    object FavouriteMoviesState : MoviesState()
}