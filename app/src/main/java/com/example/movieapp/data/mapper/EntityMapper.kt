package com.example.movieapp.data.mapper

interface EntityMapper<ENTITY, DOMAIN> {
    fun fromEntity(entity: List<ENTITY>): List<DOMAIN>
    fun toEntity(domain: DOMAIN): ENTITY
}