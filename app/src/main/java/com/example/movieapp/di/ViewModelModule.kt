package com.example.movieapp.di

import com.example.movieapp.presentation.ui.favourite_movies.viewmodel.FavouriteMoviesViewModel
import com.example.movieapp.presentation.ui.movie_details.viewmodel.MovieDetailsViewModel
import com.example.movieapp.presentation.ui.movies.viewmodel.MoviesViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel { MoviesViewModel(get()) }
    viewModel { FavouriteMoviesViewModel(get()) }
    viewModel { MovieDetailsViewModel(get()) }
}