package com.example.movieapp.data.datasource.paging_source

import com.example.movieapp.data.datasource.remote.MoviesApiService
import com.example.movieapp.data.mapper.DataMapperImpl
import com.example.movieapp.domain.model.MoviesDomain

class TopRatedMoviesPagingSource(
    private val apiService: MoviesApiService,
    private val dataMapper: DataMapperImpl
) : MoviesBasePagingSource() {


    override suspend fun moviesResponse(page: Int): List<MoviesDomain> {
        return dataMapper.dtoToDomain(apiService.getTopRatedMoves(page).body()!!)
    }
}