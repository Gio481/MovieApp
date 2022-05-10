package com.example.movieapp.data.mapper

import com.example.movieapp.data.datasource.local.entity.MoviesEntity
import com.example.movieapp.domain.model.MoviesDomain

class EntityMapperImpl : EntityMapper<MoviesEntity, MoviesDomain> {

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

    override fun toEntity(domain: MoviesDomain): MoviesEntity {
        return MoviesEntity(
            id = domain.id,
            posterPath = domain.posterPath,
            releaseDate = domain.releaseDate,
            rating = domain.rating,
            title = domain.title,
            originalTitle = domain.originalTitle,
            overview = domain.overview
        )
    }
}