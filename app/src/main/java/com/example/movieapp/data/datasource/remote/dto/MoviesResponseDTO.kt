package com.example.movieapp.data.datasource.remote.dto

import com.google.gson.annotations.SerializedName

data class MoviesResponseDTO(
    val page: Int,
    val results: List<ResultDTO>,
    @SerializedName("total_pages")
    val totalPages: Int,
    @SerializedName("total_results")
    val totalResults: Int
)