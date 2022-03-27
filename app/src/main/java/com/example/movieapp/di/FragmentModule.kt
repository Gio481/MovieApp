package com.example.movieapp.di

import com.example.movieapp.presentation.ui.movies.MoviesFragment
import com.example.movieapp.presentation.ui.movies.movies_state.MoviesState
import org.koin.androidx.fragment.dsl.fragment
import org.koin.dsl.module

val fragmentModule = module {
    factory { MoviesState.PopularMoviesState }
    factory { MoviesState.TopRatedMovies }
    fragment { MoviesFragment(get()) }
}