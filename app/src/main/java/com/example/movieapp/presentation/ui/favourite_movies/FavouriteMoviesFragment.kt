package com.example.movieapp.presentation.ui.favourite_movies

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import com.example.movieapp.databinding.FragmentFavouriteMoviesBinding
import com.example.movieapp.presentation.base.BaseFragment
import com.example.movieapp.presentation.ui.favourite_movies.adapter.FavouriteMoviesAdapter
import com.example.movieapp.presentation.ui.favourite_movies.viewmodel.FavouriteMoviesViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class FavouriteMoviesFragment :
    BaseFragment<FragmentFavouriteMoviesBinding, FavouriteMoviesViewModel>() {

    override val bindingInflater: (inflater: LayoutInflater, container: ViewGroup?, attachToRoot: Boolean) -> FragmentFavouriteMoviesBinding
        get() = FragmentFavouriteMoviesBinding::inflate

    override val viewModel: FavouriteMoviesViewModel by viewModel()

    private val movieAdapter by lazy { FavouriteMoviesAdapter() }

    override fun onBindViewModel(viewModel: FavouriteMoviesViewModel) {
        setUpRecyclerView()
        viewModel.favouriteMoviesLiveData.observe(viewLifecycleOwner) {
            movieAdapter.submitList(it)
        }
    }

    private fun setUpRecyclerView() {
        with(binding.favouriteMoviesAdapter) {
            layoutManager = GridLayoutManager(requireContext(), 2)
            adapter = movieAdapter
        }
    }

}