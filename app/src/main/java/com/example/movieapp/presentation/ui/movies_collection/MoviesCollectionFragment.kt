package com.example.movieapp.presentation.ui.movies_collection

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.example.movieapp.databinding.FragmentMoviesCollectionBinding
import com.example.movieapp.presentation.base.BaseFragment

class MoviesCollectionFragment :
    BaseFragment<FragmentMoviesCollectionBinding, MoviesCollectionViewModel>() {
    override val bindingInflater: (inflater: LayoutInflater, container: ViewGroup?, attachToRoot: Boolean) -> FragmentMoviesCollectionBinding
        get() = FragmentMoviesCollectionBinding::inflate

    override val viewModel: MoviesCollectionViewModel by viewModels()

    override fun onBindViewModel(viewModel: MoviesCollectionViewModel) {}
}