package com.example.movieapp.di

import android.content.Context
import androidx.room.Room
import com.example.movieapp.data.datasource.local.MoviesDatabase
import org.koin.dsl.module

private fun provideMoviesDatabase(context: Context): MoviesDatabase {
    return Room.databaseBuilder(context, MoviesDatabase::class.java, "movies.db").build()
}

private fun provideDao(db: MoviesDatabase) = db.moviesDao()

val databaseModule = module {
    single { provideMoviesDatabase(get()) }
    single { provideDao(get()) }
}