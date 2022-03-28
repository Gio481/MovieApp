package com.example.movieapp.presentation.ui.movie_details

import android.annotation.SuppressLint
import android.content.res.Resources
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.lifecycleScope
import com.example.movieapp.R
import com.example.movieapp.databinding.FragmentMovieDetailsBinding
import com.example.movieapp.domain.model.MoviesDomain
import com.example.movieapp.presentation.ui.movie_details.viewmodel.MovieDetailsViewModel
import com.example.movieapp.util.Constants.BUNDLE_KEY_FOR_DETAILS_FRAGMENT
import com.example.movieapp.util.extensions.image_view.setImage
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel


class MovieDetailsFragment : BottomSheetDialogFragment() {

    private var _binding: FragmentMovieDetailsBinding? = null
    private val binding get() = _binding!!
    private val viewModel: MovieDetailsViewModel by viewModel()
    private lateinit var args: MoviesDomain

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        _binding = FragmentMovieDetailsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        args = (this.arguments)?.getParcelable(BUNDLE_KEY_FOR_DETAILS_FRAGMENT)!!
        viewModel.determineBackground(args.id)
        determineBottomSheetFullScreen()
        setUpDetailScreen()
        observeBackgroundLiveData()
        setListener()
    }

    private fun observeBackgroundLiveData() {
        viewModel.backgroundLiveData.observe(viewLifecycleOwner) {
            binding.makeFavouriteFloatingActionButton.setImageResource(it)
        }
    }

    private fun determineBottomSheetFullScreen() {
        with(binding) {
            BottomSheetBehavior.from(bottomSheetLayout).apply {
                this.state = BottomSheetBehavior.STATE_EXPANDED
            }
            val layoutParams = bottomSheetLayout.layoutParams
            layoutParams.height = Resources.getSystem().displayMetrics.heightPixels
            bottomSheetLayout.layoutParams = layoutParams
        }
    }

    @SuppressLint("SetTextI18n")
    private fun setUpDetailScreen() {
        with(binding) {
            titleTextView.text = args.title
            originalTitleTextView.text = args.originalTitle
            ratingTextView.text = args.rating.toString()
            releaseDateTextView.text =
                getString(R.string.release_date) + args.releaseDate
            overviewTextView.text = args.overview
            moviePosterImageView.setImage(args.posterUrl)
        }
    }

    private fun setListener() {
        binding.makeFavouriteFloatingActionButton.setOnClickListener {
            determineOperation()
            lifecycleScope.launch {
                binding.makeFavouriteFloatingActionButton.setImageResource(
                    if (viewModel.checkSavedMovieId(args.id)) R.drawable.ic_favourite else R.drawable.ic_delete
                )
            }
        }
    }

    private fun determineOperation() {
        viewModel.determineOperation(
            movieId = args.id,
            moviesDomain = args
        )
    }

}