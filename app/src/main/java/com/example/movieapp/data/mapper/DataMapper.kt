package com.example.movieapp.data.mapper

interface DataMapper<in DTO, out DOMAIN, ENTITY, ENTITY_DOMAIN> {
    fun dtoToDomain(dto: DTO): DOMAIN
    fun fromEntity(entity: List<ENTITY>): List<ENTITY_DOMAIN>
    fun toEntity(favoriteMoviesDomain: ENTITY_DOMAIN): ENTITY
}