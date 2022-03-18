package com.example.movieapp.data.datasource.local.dao

import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.movieapp.data.datasource.local.entity.MoviesEntity

interface MoviesDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(movie: MoviesEntity)

    @Query("SELECT * FROM movies")
    suspend fun getAllMovies(): List<MoviesEntity>

    @Query("DELETE FROM movies WHERE id=:id")
    suspend fun deleteMovie(id: Long)
}