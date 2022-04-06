package com.example.movieapp.presentation.ui.movies_collection.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.movieapp.databinding.MovieItemLayoutBinding
import com.example.movieapp.domain.model.MoviesDomain
import com.example.movieapp.util.extensions.image_view.setImage

class MoviesAdapter(private val onItemClickListener: OnItemClickListener) :
    RecyclerView.Adapter<MoviesAdapter.MoviesViewHolder>() {

    private val itemList = mutableListOf<MoviesDomain>()

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
        holder.onBind(itemList[position])
    }

    class MoviesViewHolder(
        private val binding: MovieItemLayoutBinding,
        private val onItemClickListener: OnItemClickListener,
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

    override fun getItemCount(): Int {
        return itemList.size
    }

    fun nextPage(list: MutableList<MoviesDomain>) {
        itemList.clear()
        itemList.addAll(list)
        notifyDataSetChanged()
    }
}