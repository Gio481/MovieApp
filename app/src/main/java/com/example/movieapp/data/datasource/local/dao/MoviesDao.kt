package com.example.movieapp.data.datasource.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.movieapp.data.datasource.local.entity.MoviesEntity

@Dao
interface MoviesDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(movie: MoviesEntity)

    @Query("SELECT posterPath FROM movies")
    suspend fun getAllMoviesPoster(): List<String>

    @Query("SELECT * FROM movies")
    suspend fun getAllMovies(): List<MoviesEntity>

    @Query("DELETE FROM movies WHERE posterPath=:posterPath")
    suspend fun deleteMovie(posterPath: String)
}