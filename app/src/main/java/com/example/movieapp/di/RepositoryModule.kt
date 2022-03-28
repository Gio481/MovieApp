package com.example.movieapp.di

import com.example.movieapp.data.mapper.DataMapperImpl
import com.example.movieapp.data.repository.favourite_movies.FavouriteMoviesRepositoryImpl
import com.example.movieapp.data.repository.movies.MoviesRepositoryImpl
import com.example.movieapp.data.repository.movies_detail.MoviesDetailRepositoryImpl
import com.example.movieapp.domain.repository.favourite_movies.FavouriteMoviesRepository
import com.example.movieapp.domain.repository.movies.MoviesRepository
import com.example.movieapp.domain.repository.movies_detail.MoviesDetailRepository
import org.koin.dsl.module

val repositoryModule = module {
    single { DataMapperImpl() }
    single<MoviesRepository> { MoviesRepositoryImpl(get(), get()) }
    single<MoviesDetailRepository> { MoviesDetailRepositoryImpl(get(), get()) }
    single<FavouriteMoviesRepository> { FavouriteMoviesRepositoryImpl(get(), get()) }
}