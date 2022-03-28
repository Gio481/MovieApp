package com.example.movieapp.presentation.ui.fragment_container

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.movieapp.R
import com.example.movieapp.databinding.FragmentContainerBinding
import com.example.movieapp.presentation.ui.favourite_movies.FavouriteMoviesFragment
import com.example.movieapp.presentation.ui.movies.MoviesFragment
import com.example.movieapp.presentation.ui.movies.movies_state.MoviesState
import com.example.movieapp.util.extensions.fragment.transaction

class ContainerFragment : Fragment() {
    private var _binding: FragmentContainerBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        _binding = FragmentContainerBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        fragmentTransaction(MoviesFragment(MoviesState.TopRatedMovies))
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

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}