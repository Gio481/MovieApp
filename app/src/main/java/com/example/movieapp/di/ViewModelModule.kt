package com.example.movieapp.di

import com.example.movieapp.presentation.ui.movie_details.viewmodel.MovieDetailsViewModel
import com.example.movieapp.presentation.ui.movies_collection.viewmodel.MoviesCollectionViewModel
import com.example.movieapp.presentation.ui.splash.viewmodel.SplashViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel { MovieDetailsViewModel(get()) }
    viewModel { MoviesCollectionViewModel(get(), get()) }
    viewModel { SplashViewModel() }
}