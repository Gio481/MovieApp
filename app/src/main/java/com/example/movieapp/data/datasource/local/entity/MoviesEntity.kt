package com.example.movieapp.data.datasource.local.entity

import androidx.room.Entity

@Entity(tableName = "movies")
data class MoviesEntity(
    val id: Long,
    val poster: String,
)