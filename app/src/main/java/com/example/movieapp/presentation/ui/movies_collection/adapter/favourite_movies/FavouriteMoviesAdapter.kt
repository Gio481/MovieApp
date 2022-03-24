package com.example.movieapp.presentation.ui.movies_collection.adapter.favourite_movies

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.movieapp.databinding.FavouriteMoviesRowLayoutBinding
import com.example.movieapp.domain.model.FavouriteMoviesDomain
import com.example.movieapp.util.ItemsDIffUtil
import com.example.movieapp.util.extensions.image_view.setImage

class FavouriteMoviesAdapter :
    ListAdapter<FavouriteMoviesDomain, FavouriteMoviesAdapter.ViewHolder>(ItemsDIffUtil()) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            FavouriteMoviesRowLayoutBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.onBind(getItem(position))
    }

    class ViewHolder(private val binding: FavouriteMoviesRowLayoutBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun onBind(movies: FavouriteMoviesDomain) {
            with(binding) {
                moviePosterImageView.setImage(movies.poster)
                releaseDateTextView.text = movies.releaseDate
                ratingTextView.text = movies.rating.toString()
            }
        }
    }
}