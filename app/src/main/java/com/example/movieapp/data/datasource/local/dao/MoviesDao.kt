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

    @Query("SELECT id FROM movies")
    suspend fun getAllMoviesID(): List<Int>

    @Query("SELECT * FROM movies")
    suspend fun getAllMovies(): List<MoviesEntity>

    @Query("DELETE FROM movies WHERE id=:id")
    suspend fun deleteMovie(id: Int)
}