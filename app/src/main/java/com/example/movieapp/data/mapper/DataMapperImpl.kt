package com.example.movieapp.data.mapper

import com.example.movieapp.data.datasource.local.entity.MoviesEntity
import com.example.movieapp.data.datasource.remote.dto.MoviesDTO
import com.example.movieapp.domain.model.MoviesDomain

class DataMapperImpl : DataMapper<MoviesDTO, MoviesDomain, MoviesEntity> {

    override fun dtoToDomain(dto: MoviesDTO): MoviesDomain {
        return MoviesDomain(
            id = dto.id,
            title = dto.title,
            posterPath = dto.posterPath,
            originalTitle = dto.originalTitle,
            overview = dto.overview,
            rating = dto.voteAverage,
            releaseDate = dto.releaseDate
        )
    }

    override fun fromEntity(entity: MoviesEntity): MoviesDomain {
        return MoviesDomain(
            id = entity.id,
            posterPath = entity.posterPath,
            releaseDate = entity.releaseDate,
            rating = entity.rating,
            overview = entity.overview,
            title = entity.title,
            originalTitle = entity.originalTitle
        )
    }

    override fun toEntity(favoriteMovie: MoviesDomain): MoviesEntity {
        return MoviesEntity(
            id = favoriteMovie.id,
            posterPath = favoriteMovie.posterPath,
            releaseDate = favoriteMovie.releaseDate,
            rating = favoriteMovie.rating,
            title = favoriteMovie.title,
            originalTitle = favoriteMovie.originalTitle,
            overview = favoriteMovie.overview
        )
    }
}