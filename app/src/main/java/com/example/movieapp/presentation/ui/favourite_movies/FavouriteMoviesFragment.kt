package com.example.movieapp.presentation.ui.favourite_movies

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import androidx.recyclerview.widget.GridLayoutManager
import com.example.movieapp.R
import com.example.movieapp.databinding.FragmentFavouriteMoviesBinding
import com.example.movieapp.domain.model.FavouriteMoviesDomain
import com.example.movieapp.presentation.base.BaseMoviesFragment
import com.example.movieapp.presentation.ui.favourite_movies.adapter.FavouriteMoviesAdapter
import com.example.movieapp.presentation.ui.favourite_movies.viewmodel.FavouriteMoviesViewModel
import com.example.movieapp.presentation.ui.movie_details.MovieDetailsFragment
import com.example.movieapp.presentation.ui.movies.adapter.OnItemClickListener
import org.koin.androidx.viewmodel.ext.android.viewModel

class FavouriteMoviesFragment :
    BaseMoviesFragment<FragmentFavouriteMoviesBinding, FavouriteMoviesViewModel>(),
    OnItemClickListener<FavouriteMoviesDomain> {

    override val bindingInflater: (inflater: LayoutInflater, container: ViewGroup?, attachToRoot: Boolean) -> FragmentFavouriteMoviesBinding
        get() = FragmentFavouriteMoviesBinding::inflate

    override val viewModel: FavouriteMoviesViewModel by viewModel()

    private val detailsBottomSheet by lazy { MovieDetailsFragment() }

    private val movieAdapter by lazy { FavouriteMoviesAdapter(this) }

    override fun onBindViewModel(viewModel: FavouriteMoviesViewModel) {
        detailsBottomSheet.setStyle(DialogFragment.STYLE_NORMAL, R.style.BottomSheetTheme)
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

    override fun onItemClick(detail: FavouriteMoviesDomain) {
        val s = bundle {
            putParcelable("giorgi", detail)
        }
        detailsBottomSheet.arguments = s
        detailsBottomSheet.show(childFragmentManager, null)
    }

    private fun bundle(block: Bundle.() -> Unit): Bundle {
        return Bundle().apply(block)
    }


}