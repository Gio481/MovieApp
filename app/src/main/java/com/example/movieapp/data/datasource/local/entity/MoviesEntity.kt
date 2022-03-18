package com.example.movieapp.data.datasource.local.entity

import androidx.room.Entity
import com.example.movieapp.util.Constants.DATABASE_TABLE_NAME

@Entity(tableName = DATABASE_TABLE_NAME)
data class MoviesEntity(
    val id: Long,
    val poster: String,
)