package com.example.movieapp.presentation.ui.movie_details

import android.content.res.Resources
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.lifecycleScope
import com.example.movieapp.R
import com.example.movieapp.databinding.FragmentMovieDetailBinding
import com.example.movieapp.domain.model.MoviesDomain
import com.example.movieapp.presentation.ui.movie_details.viewmodel.MovieDetailsViewModel
import com.example.movieapp.util.Constants.BUNDLE_KEY_FOR_MOVIE_DETAILS
import com.example.movieapp.util.extensions.image_view.setImage
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.getViewModel

class MovieDetailFragment : BottomSheetDialogFragment() {
    private var _binding: FragmentMovieDetailBinding? = null
    private val binding get() = _binding!!
    private lateinit var viewModel: MovieDetailsViewModel
    private lateinit var args: MoviesDomain

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        _binding = FragmentMovieDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = getViewModel(clazz = MovieDetailsViewModel::class)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        args = arguments?.getParcelable(BUNDLE_KEY_FOR_MOVIE_DETAILS)!!
        setUpFullScreenBottomSheet()
        onBindViewModel(viewModel)
    }

    private fun setUpFullScreenBottomSheet() {
        with(binding) {
            val layoutParams = detailsBottomSheetLayout.layoutParams
            layoutParams?.height = Resources.getSystem().displayMetrics.heightPixels
            detailsBottomSheetLayout.layoutParams = layoutParams
        }
    }

    private fun onBindViewModel(viewModel: MovieDetailsViewModel) {
        viewModel.determineActionButtonBackground(args.posterPath)
        setUpDetailFragment()
        observeActionButtonBackground(viewModel)
        setListener(viewModel)
    }

    private fun setUpDetailFragment() {
        with(binding) {
            titleTextView.text = args.title
            moviePosterImageView.setImage(args.posterUrl)
            originalTitleTextView.text = args.originalTitle
            overviewTextView.text = args.overview
            releaseDateTextView.text = args.releaseDate
            ratingTextView.text = args.rating.toString()
        }
    }

    private fun observeActionButtonBackground(viewModel: MovieDetailsViewModel) {
        viewModel.actionButtonBackgroundLiveData.observe(viewLifecycleOwner) {
            binding.makeFavouriteFloatingActionButton.setImageResource(it)
        }
    }

    private fun setListener(viewModel: MovieDetailsViewModel) {
        with(binding.makeFavouriteFloatingActionButton) {
            setOnClickListener {
                lifecycleScope.launch {
                    setImageResource(if (viewModel.isMovieSaved(args.posterPath)) R.drawable.ic_favourite else R.drawable.ic_delete)
                    viewModel.determineInsertOrDelete(args.posterPath, args)
                }
            }
        }
    }
}