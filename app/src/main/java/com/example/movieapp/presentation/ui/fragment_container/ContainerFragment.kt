package com.example.movieapp.presentation.ui.fragment_container

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.movieapp.R
import com.example.movieapp.databinding.FragmentContainerBinding
import com.example.movieapp.presentation.base.BaseFragment
import com.example.movieapp.presentation.ui.favourite_movies.FavouriteMoviesFragment
import com.example.movieapp.presentation.ui.movies.MoviesFragment
import com.example.movieapp.presentation.ui.movies.movies_state.MoviesState
import com.example.movieapp.util.extensions.fragment.transaction

class ContainerFragment : BaseFragment<FragmentContainerBinding>() {

    override val bindingInflater: (inflater: LayoutInflater, container: ViewGroup?, attachToRoot: Boolean) -> FragmentContainerBinding
        get() = FragmentContainerBinding::inflate

    override fun initial() {
        determineFragmentTransaction()
    }

    private fun determineFragmentTransaction() {
        binding.movieStateRadioGroup.setOnCheckedChangeListener { _, i ->
            when (i) {
                R.id.topRatedRadioButton -> fragmentTransaction(MoviesFragment(MoviesState.TopRatedMovies))
                R.id.popularRadioButton -> fragmentTransaction(MoviesFragment(MoviesState.PopularMoviesState))
                R.id.favoriteRadioButton -> fragmentTransaction(FavouriteMoviesFragment())
            }
        }
    }

    private fun fragmentTransaction(fragment: Fragment) {
        childFragmentManager.transaction(fragment)
    }
}