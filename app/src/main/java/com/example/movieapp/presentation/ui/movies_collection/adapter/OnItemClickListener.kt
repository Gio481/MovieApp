package com.example.movieapp.presentation.ui.movies_collection.adapter

import com.example.movieapp.domain.model.MoviesDomain

interface OnItemClickListener {
    fun onItemClick(movie:MoviesDomain)
}