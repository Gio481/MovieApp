package com.example.movieapp.di

import com.example.movieapp.presentation.ui.favourite_movies.viewmodel.FavouriteMoviesViewModel
import com.example.movieapp.presentation.ui.movies.viewmodel.MoviesViewModel
import com.example.movieapp.presentation.ui.splash.viewmodel.SplashScreenViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel { SplashScreenViewModel() }
    viewModel { MoviesViewModel(get()) }
    viewModel { FavouriteMoviesViewModel(get()) }
}