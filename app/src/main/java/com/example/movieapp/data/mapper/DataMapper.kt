package com.example.movieapp.data.mapper

interface DataMapper<in DTO, DOMAIN, ENTITY> {
    fun dtoToDomain(dto: DTO): DOMAIN
    fun fromEntity(entity: ENTITY): DOMAIN
    fun toEntity(favoriteMovie: DOMAIN): ENTITY
}