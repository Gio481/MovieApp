package com.example.movieapp.data.datasource.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.movieapp.data.datasource.local.dao.MoviesDao
import com.example.movieapp.data.datasource.local.entity.MoviesEntity

@Database(entities = [MoviesEntity::class], version = 1)
abstract class MoviesDatabase : RoomDatabase() {
    abstract fun moviesDao(): MoviesDao
}