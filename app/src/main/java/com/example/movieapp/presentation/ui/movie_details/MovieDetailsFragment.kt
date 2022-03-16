package com.example.movieapp.presentation.ui.movie_details

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.example.movieapp.databinding.FragmentMovieDetailsBinding
import com.example.movieapp.presentation.base.BaseFragment

class MovieDetailsFragment : BaseFragment<FragmentMovieDetailsBinding, MovieDetailsViewModel>() {
    override val bindingInflater: (inflater: LayoutInflater, container: ViewGroup?, attachToRoot: Boolean) -> FragmentMovieDetailsBinding
        get() = FragmentMovieDetailsBinding::inflate

    override val viewModel: MovieDetailsViewModel by viewModels()

    override fun onBindViewModel(viewModel: MovieDetailsViewModel) {}
}