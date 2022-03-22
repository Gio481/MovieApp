package com.example.movieapp.data.mapper

import com.example.movieapp.data.datasource.local.entity.MoviesEntity
import com.example.movieapp.data.datasource.remote.dto.MoviesResponseDTO
import com.example.movieapp.domain.model.FavouriteMoviesDomain
import com.example.movieapp.domain.model.MoviesDomain

class DataMapperClass :
    DataMapper<MoviesResponseDTO, List<MoviesDomain>, MoviesEntity, FavouriteMoviesDomain> {

    override fun moviesDomain(dto: MoviesResponseDTO): List<MoviesDomain> {
        return dto.results.map {
            MoviesDomain(
                title = it.title,
                posterPath = it.posterPath,
                originalTitle = it.originalTitle,
                overview = it.overview,
                rating = it.voteAverage,
                releaseDate = it.releaseDate
            )
        }
    }

    override fun fromEntity(entity: List<MoviesEntity>): List<FavouriteMoviesDomain> {
        return entity.map {
            FavouriteMoviesDomain(
                id = it.id,
                poster = it.poster,
                releaseDate = it.releaseDate,
                rating = it.rating
            )
        }
    }

    override fun toEntity(favoriteMoviesDomain: FavouriteMoviesDomain): MoviesEntity {
        return MoviesEntity(
            id = favoriteMoviesDomain.id,
            poster = favoriteMoviesDomain.poster,
            releaseDate = favoriteMoviesDomain.releaseDate,
            rating = favoriteMoviesDomain.rating
        )
    }
}