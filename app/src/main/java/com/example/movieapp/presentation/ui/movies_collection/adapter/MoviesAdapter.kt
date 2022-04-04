package com.example.movieapp.presentation.ui.movies_collection.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.movieapp.databinding.MovieItemLayoutBinding
import com.example.movieapp.domain.model.MoviesDomain
import com.example.movieapp.util.ItemsDIffUtil
import com.example.movieapp.util.extensions.image_view.setImage

class MoviesAdapter(private val onItemClickListener: OnItemClickListener) : PagingDataAdapter<MoviesDomain, MoviesAdapter.MoviesViewHolder>(ItemsDIffUtil()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MoviesViewHolder {
        return MoviesViewHolder(
            MovieItemLayoutBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            ),
            onItemClickListener
        )
    }


    override fun onBindViewHolder(holder: MoviesViewHolder, position: Int) {
        getItem(position)?.let { holder.onBind(it) }
    }

    class MoviesViewHolder(
        private val binding: MovieItemLayoutBinding,
        private val onItemClickListener: OnItemClickListener
    ) : RecyclerView.ViewHolder(binding.root) {

        fun onBind(moviesDomain: MoviesDomain) {
            with(binding) {
                moviePosterImageView.setImage(moviesDomain.posterUrl)
                ratingTextView.text = moviesDomain.rating.toString()
                releaseDateTextView.text = moviesDomain.releaseDate
                moviePosterImageView.setOnClickListener {
                    onItemClickListener.onItemClick(moviesDomain)
                }
            }
        }
    }
}