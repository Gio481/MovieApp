package com.example.movieapp.data.datasource.remote

import com.example.movieapp.data.datasource.remote.dto.MoviesResponseDTO
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface MoviesApiService {
    @GET("movie/top_rated")
    suspend fun getTopRatedMoves(
        @Query("page") page:Int
    ):Response<MoviesResponseDTO>

    @GET("movie/popular")
    suspend fun getPopularMovies(
        @Query("page") page: Int
    ):Response<MoviesResponseDTO>
}