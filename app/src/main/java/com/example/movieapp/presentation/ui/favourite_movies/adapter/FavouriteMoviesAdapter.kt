package com.example.movieapp.presentation.ui.favourite_movies.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.movieapp.databinding.FavouriteMovieItemLayoutBinding
import com.example.movieapp.domain.model.MoviesDomain
import com.example.movieapp.presentation.ui.movies.adapter.OnItemClickListener
import com.example.movieapp.util.ItemsDIffUtil
import com.example.movieapp.util.extensions.image_view.setImage

class FavouriteMoviesAdapter(private val onItemClickListener: OnItemClickListener<MoviesDomain>) :
    ListAdapter<MoviesDomain, FavouriteMoviesAdapter.ViewHolder>(ItemsDIffUtil()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            FavouriteMovieItemLayoutBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            ),
            onItemClickListener
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        getItem(position)?.let { holder.onBind(it) }
    }

    class ViewHolder(
        private val binding: FavouriteMovieItemLayoutBinding,
        private val onItemClickListener: OnItemClickListener<MoviesDomain>,
    ) :
        RecyclerView.ViewHolder(binding.root) {
        fun onBind(movies: MoviesDomain) {
            with(binding) {
                moviePosterImageView.setImage(movies.posterUrl)
                releaseDateTextView.text = movies.releaseDate
                ratingTextView.text = movies.rating.toString()
                moviePosterImageView.setOnClickListener {
                    onItemClickListener.onItemClick(movies)
                }
            }
        }
    }
}