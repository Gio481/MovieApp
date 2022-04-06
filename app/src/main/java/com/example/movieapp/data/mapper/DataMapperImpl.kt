package com.example.movieapp.data.mapper

import com.example.movieapp.data.datasource.local.entity.MoviesEntity
import com.example.movieapp.data.datasource.remote.dto.MoviesResponseDTO
import com.example.movieapp.domain.model.MoviesDomain

class DataMapperImpl : DataMapper<MoviesResponseDTO, MoviesDomain, MoviesEntity> {

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

    override fun fromEntity(entity: List<MoviesEntity>): List<MoviesDomain> {
        return entity.map {
            MoviesDomain(
                id = it.id,
                posterPath = it.posterPath,
                releaseDate = it.releaseDate,
                rating = it.rating,
                overview = it.overview,
                title = it.title,
                originalTitle = it.originalTitle
            )
        }
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