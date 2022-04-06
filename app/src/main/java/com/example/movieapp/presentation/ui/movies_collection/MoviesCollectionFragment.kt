package com.example.movieapp.presentation.ui.movies_collection

import android.content.res.Configuration
import android.os.Bundle
import android.util.Log.d
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.DialogFragment.STYLE_NORMAL
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.GridLayoutManager
import com.example.movieapp.R
import com.example.movieapp.databinding.FragmentMoviesCollectionBinding
import com.example.movieapp.domain.model.MoviesDomain
import com.example.movieapp.presentation.base.BaseFragment
import com.example.movieapp.presentation.ui.movie_details.MovieDetailFragment
import com.example.movieapp.presentation.ui.movies_collection.adapter.MoviesAdapter
import com.example.movieapp.presentation.ui.movies_collection.adapter.OnItemClickListener
import com.example.movieapp.presentation.ui.movies_collection.adapter.OnScrollListener
import com.example.movieapp.presentation.ui.movies_collection.movies_state.MoviesState
import com.example.movieapp.presentation.ui.movies_collection.viewmodel.MoviesCollectionViewModel
import com.example.movieapp.util.Constants.BUNDLE_KEY_FOR_MOVIE_DETAILS
import com.example.movieapp.util.Constants.DATA_REFRESHING_TIME
import com.example.movieapp.util.NetworkManager
import com.example.movieapp.util.NetworkState
import com.example.movieapp.util.extensions.dialog.createDialog
import com.example.movieapp.util.extensions.fragment.observer
import com.example.movieapp.util.extensions.fragment.showToast
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlin.reflect.KClass

class MoviesCollectionFragment :
    BaseFragment<FragmentMoviesCollectionBinding, MoviesCollectionViewModel>(),
    OnItemClickListener {

    override val bindingInflater: (inflater: LayoutInflater, container: ViewGroup?, attachToRoot: Boolean) -> FragmentMoviesCollectionBinding
        get() = FragmentMoviesCollectionBinding::inflate

    override fun getViewModelClass(): KClass<MoviesCollectionViewModel> =
        MoviesCollectionViewModel::class

    private val bottomSheetFragment by lazy { MovieDetailFragment() }

    private val moviesAdapter by lazy { MoviesAdapter(this) }

    private var gridLayoutColumn = 2

    private var internet: Boolean? = null

    override fun onBindViewModel(viewModel: MoviesCollectionViewModel) {
        if (!viewModel.isFirstChecked) {
            binding.topRatedRadioButton.isChecked = true
            viewModel.isFirstChecked = true
        }
        setUpRecyclerView(gridLayoutColumn, viewModel)
        observeMovies(viewModel)
        getMovies(viewModel)
        observeErrorLiveData(viewModel)
        setDataRefreshingListener(viewModel)
        observeInternetConnection()
    }

    private fun observeInternetConnection() {
        val networkManager = NetworkManager(requireContext())
        observer(networkManager) {

        }
        networkManager.observe(viewLifecycleOwner) {
            when (it) {
                is NetworkState.Available -> {
                    internet = true

                }
                is NetworkState.UnAvailable -> {
                    internet = false
                    showToast(getString(R.string.internet_error_text))
                }
                else -> internet = null
            }
        }
    }

    private fun observeMovies(viewModel: MoviesCollectionViewModel) {
        isProgressBarVisible(true)
        observer(viewModel.moviesLiveData) {
            moviesAdapter.loadData(it.toMutableList())
            isProgressBarVisible(false)
        }
    }

    private fun observeErrorLiveData(viewModel: MoviesCollectionViewModel) {
        observer(viewModel.errorLiveData) {
            isProgressBarVisible(false)
            showToast(it)
        }
    }

    private fun getMovies(viewModel: MoviesCollectionViewModel) {
        binding.movieStateRadioGroup.setOnCheckedChangeListener { _, i ->
            when (i) {
                R.id.topRatedRadioButton -> {
                    viewModel.getMovieState = MoviesState.TopRatedMoviesState
                    viewModel.getMovies(MoviesState.TopRatedMoviesState)
                }

                R.id.popularRadioButton -> {
                    viewModel.getMovieState = MoviesState.PopularMoviesState
                    viewModel.getMovies(MoviesState.PopularMoviesState)
                }
                R.id.favoriteRadioButton -> {
                    viewModel.getMovieState = MoviesState.FavouriteMoviesState
                    viewModel.getMovies(MoviesState.FavouriteMoviesState)
                }
            }
        }
    }

    private fun setUpRecyclerView(gridLayoutColumn: Int, viewModel: MoviesCollectionViewModel) {
        val manager = GridLayoutManager(requireContext(), gridLayoutColumn)
        with(binding.moviesRecyclerView) {
            layoutManager = manager
            adapter = moviesAdapter
            addOnScrollListener(OnScrollListener {
                if (internet == false) {
                    showDialog()
                } else {
                    viewModel.getMovies(viewModel.getMovieState)
                }
            })
        }
    }

    private fun setDataRefreshingListener(viewModel: MoviesCollectionViewModel) {
        with(binding.moviesRefreshLayout) {
            setOnRefreshListener {
                viewLifecycleOwner.lifecycleScope.launch {
                    viewModel.getMovies(viewModel.getMovieState)
                    delay(DATA_REFRESHING_TIME)
                    isRefreshing = false
                }
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        gridLayoutColumn =
            if (requireActivity().resources.configuration.orientation == Configuration.ORIENTATION_PORTRAIT) {
                2
            } else {
                3
            }
    }

    private fun isProgressBarVisible(visibility: Boolean) {
        binding.progressBar.isVisible = visibility
    }

    private fun showDialog() {
        requireContext().createDialog(getString(R.string.internet_error_text))
    }

    override fun onItemClick(movie: MoviesDomain) {
        bottomSheetFragment.setStyle(STYLE_NORMAL, R.style.BottomSheetTheme)
        bottomSheetFragment.arguments = bundle {
            putParcelable(BUNDLE_KEY_FOR_MOVIE_DETAILS, movie)
        }
        bottomSheetFragment.show(childFragmentManager, null)
    }

    private fun bundle(block: Bundle.() -> Unit): Bundle {
        return Bundle().apply(block)
    }
}