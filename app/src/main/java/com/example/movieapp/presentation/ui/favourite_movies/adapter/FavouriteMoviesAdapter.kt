package com.example.movieapp.presentation.ui.favourite_movies.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.movieapp.databinding.FavouriteMovieItemLayoutBinding
import com.example.movieapp.domain.model.FavouriteMoviesDomain
import com.example.movieapp.util.ItemsDIffUtil
import com.example.movieapp.util.extensions.image_view.setImage

class FavouriteMoviesAdapter :
    ListAdapter<FavouriteMoviesDomain, FavouriteMoviesAdapter.ViewHolder>(ItemsDIffUtil()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            FavouriteMovieItemLayoutBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        getItem(position)?.let { holder.onBind(it) }
    }

    class ViewHolder(private val binding: FavouriteMovieItemLayoutBinding) :
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