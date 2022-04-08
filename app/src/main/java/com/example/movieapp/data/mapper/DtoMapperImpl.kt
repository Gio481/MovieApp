package com.example.movieapp.data.mapper

import com.example.movieapp.data.datasource.remote.dto.MoviesResponseDTO
import com.example.movieapp.domain.model.MoviesDomain

class DtoMapperImpl : DtoMapper<MoviesResponseDTO, MoviesDomain> {

    override fun dtoToDomain(dto: MoviesResponseDTO): List<MoviesDomain> {
        return dto.results.map {
            MoviesDomain(
                id = it.id,
                title = it.title,
                posterPath = it.posterPath,
                originalTitle = it.originalTitle,
                overview = it.overview,
                rating = it.voteAverage,
                releaseDate = it.releaseDate
            )
        }
    }
}