package com.example.movieapp.di

import com.example.movieapp.data.mapper.DataMapperClass
import com.example.movieapp.data.repository.movies.MoviesRepositoryImpl
import com.example.movieapp.data.repository.movies_detail.MoviesDetailRepositoryImpl
import com.example.movieapp.domain.repository.movies.MoviesRepository
import com.example.movieapp.domain.repository.movies_detail.MoviesDetailRepository
import org.koin.dsl.module

val repositoryModule = module {
    single { DataMapperClass() }
    single<MoviesRepository> { MoviesRepositoryImpl(get(), get(), get()) }
    single<MoviesDetailRepository> { MoviesDetailRepositoryImpl(get(), get()) }
}