package com.example.movieapp.data.datasource.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.movieapp.util.Constants.DATABASE_TABLE_NAME

@Entity(tableName = DATABASE_TABLE_NAME)
data class MoviesEntity(
    @PrimaryKey(autoGenerate = false) val id: Long? = null,
    val poster: String,
)