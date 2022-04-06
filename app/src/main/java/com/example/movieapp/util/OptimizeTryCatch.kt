package com.example.movieapp.util

import com.example.movieapp.data.datasource.remote.dto.MoviesResponseDTO
import com.example.movieapp.data.mapper.DataMapperImpl
import com.example.movieapp.domain.model.MoviesDomain
import retrofit2.Response

inline fun dataFetcher(
    dataMapperImpl: DataMapperImpl,
    apiResponse: () -> Response<MoviesResponseDTO>,
): Resources<List<MoviesDomain>> {
    return try {
        val response = apiResponse.invoke()
        if (response.isSuccessful) {
            Resources.Success(dataMapperImpl.dtoToDomain(response.body()!!))
        } else {
            Resources.Error(response.message())
        }
    } catch (e: Exception) {
        Resources.Error(e.message!!)
    }
}