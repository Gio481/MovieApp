package com.example.movieapp.data.mapper

interface DataMapper<in MOVIES_RESPONSE_DTO, out MOVIES_DOMAIN, ENTITY, FAVORITE_MOVIES_DOMAIN> {
    fun moviesDomain(dto: MOVIES_RESPONSE_DTO): MOVIES_DOMAIN
    fun fromEntity(entity: List<ENTITY>): List<FAVORITE_MOVIES_DOMAIN>
    fun toEntity(favoriteMoviesDomain: FAVORITE_MOVIES_DOMAIN): ENTITY
}