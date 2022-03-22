package com.example.movieapp.presentation.ui.movies_collection.adapter.movies.paging_source

import com.example.movieapp.data.datasource.remote.MoviesApiService
import com.example.movieapp.data.datasource.remote.dto.MoviesResponseDTO
import com.example.movieapp.data.mapper.DataMapperClass
import com.example.movieapp.domain.model.MoviesDomain
import retrofit2.Response

class TopRatedMoviesPagingSource(
    private val apiService: MoviesApiService,
    private val dataMapper: DataMapperClass
) : MoviesBasePagingSource() {

    override suspend fun moviesResponse(page: Int): Response<MoviesResponseDTO> {
        return apiService.getTopRatedMoves(page)
    }

    override suspend fun movies(page: Int): List<MoviesDomain> {
        return dataMapper.moviesDomain(moviesResponse(page).body()!!)
    }
}