package com.example.movieapp.di

import com.example.movieapp.domain.interactor.usecases.get_movies.GetMoviesUseCase
import com.example.movieapp.domain.interactor.usecases.get_movies.GetMoviesUseCaseImpl
import com.example.movieapp.domain.interactor.usecases.get_movies_detail.GetMoviesDetailUseCase
import com.example.movieapp.domain.interactor.usecases.get_movies_detail.GetMoviesDetailUseCaseImpl
import org.koin.dsl.module

val useCaseModule = module {
    single<GetMoviesUseCase> { GetMoviesUseCaseImpl(get()) }
    single<GetMoviesDetailUseCase> { GetMoviesDetailUseCaseImpl(get()) }
}