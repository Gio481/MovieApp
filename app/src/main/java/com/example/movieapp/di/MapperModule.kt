package com.example.movieapp.di

import com.example.movieapp.data.datasource.local.entity.MoviesEntity
import com.example.movieapp.data.datasource.remote.dto.MoviesResponseDTO
import com.example.movieapp.data.mapper.DtoMapper
import com.example.movieapp.data.mapper.DtoMapperImpl
import com.example.movieapp.data.mapper.EntityMapper
import com.example.movieapp.data.mapper.EntityMapperImpl
import com.example.movieapp.domain.model.MoviesDomain
import org.koin.dsl.module

val mapperModule = module {
    single<DtoMapper<MoviesResponseDTO, MoviesDomain>> { DtoMapperImpl() }
    single<EntityMapper<MoviesEntity, MoviesDomain>> { EntityMapperImpl() }
}