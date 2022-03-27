package com.example.movieapp.data.datasource.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "movies")
data class MoviesEntity(
    @PrimaryKey(autoGenerate = false) val id: Int,
    val poster: String,
    val releaseDate: String,
    val rating: Double
)