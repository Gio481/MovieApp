package com.example.movieapp.data.mapper

interface DataMapper<in DTO, DOMAIN, ENTITY> {
    fun dtoToDomain(dto: DTO): List<DOMAIN>
    fun fromEntity(entity: List<ENTITY>): List<DOMAIN>
    fun toEntity(favoriteMovie: DOMAIN): ENTITY
}