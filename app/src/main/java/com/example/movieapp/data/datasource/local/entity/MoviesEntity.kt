package com.example.movieapp.data.datasource.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "movies")
data class MoviesEntity(
    @PrimaryKey(autoGenerate = false) val id: Int,
    val posterPath: String,
    val posterUrl:String= "https://image.tmdb.org/t/p/w185$posterPath",
    val releaseDate: String,
    val rating: Double,
    val originalTitle: String,
    val overview: String,
    val title: String,
)