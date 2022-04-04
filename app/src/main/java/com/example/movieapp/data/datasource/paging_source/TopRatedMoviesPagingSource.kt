package com.example.movieapp.data.datasource.paging_source

import com.example.movieapp.data.datasource.remote.MoviesApiService
import com.example.movieapp.data.datasource.remote.dto.MoviesDTO

class TopRatedMoviesPagingSource(
    private val apiService: MoviesApiService,
) : MoviesBasePagingSource() {

    override suspend fun moviesResponse(page: Int): List<MoviesDTO>? {
        val s = apiService.getTopRatedMoves(page)
        return if (s.isSuccessful) {
            s.body()?.results
        } else {
            null
        }
    }
}