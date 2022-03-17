package com.example.movieapp.di

import android.content.Context
import androidx.room.Room
import com.example.movieapp.data.datasource.local.MoviesDatabase
import com.example.movieapp.util.Constants.DATABASE_NAME
import org.koin.dsl.module

private fun provideMoviesDatabase(context: Context): MoviesDatabase {
    return Room.databaseBuilder(context, MoviesDatabase::class.java, DATABASE_NAME).build()
}

private fun provideDao(db: MoviesDatabase) = db.moviesDao()

val databaseModule = module {
    single { provideMoviesDatabase(get()) }
    single { provideDao(get()) }
}