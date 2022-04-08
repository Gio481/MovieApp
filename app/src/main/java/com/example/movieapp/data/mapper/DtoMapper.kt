package com.example.movieapp.data.mapper

interface DtoMapper<in DTO, out DOMAIN> {
    fun dtoToDomain(dto: DTO): List<DOMAIN>
}