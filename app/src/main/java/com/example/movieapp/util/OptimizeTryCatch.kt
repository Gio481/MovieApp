package com.example.movieapp.util

import com.example.movieapp.data.mapper.DtoMapper
import retrofit2.Response

inline fun <DTO, DOMAIN> dataFetcher(
    dtoMapper: DtoMapper<DTO, DOMAIN>,
    apiResponse: () -> Response<DTO>,
): Resources<List<DOMAIN>> {
    return try {
        val response = apiResponse.invoke()
        if (response.isSuccessful) {
            Resources.Success(dtoMapper.dtoToDomain(response.body()!!))
        } else {
            Resources.Error(response.message())
        }
    } catch (e: Exception) {
        Resources.Error(e.message!!)
    }
}