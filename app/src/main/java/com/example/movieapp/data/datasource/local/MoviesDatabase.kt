package com.example.movieapp.data.datasource.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.movieapp.data.datasource.local.dao.MoviesDao
import com.example.movieapp.data.datasource.local.entity.MoviesEntity
import com.example.movieapp.util.Constants.DATABASE_VERSION

@Database(entities = [MoviesEntity::class], version = DATABASE_VERSION)
abstract class MoviesDatabase : RoomDatabase() {
    abstract fun moviesDao(): MoviesDao
}