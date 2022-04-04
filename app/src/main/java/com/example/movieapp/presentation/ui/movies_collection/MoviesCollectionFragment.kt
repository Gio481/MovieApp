package com.example.movieapp.presentation.ui.movies_collection

import android.content.res.Configuration
import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.DialogFragment.STYLE_NORMAL
import androidx.lifecycle.lifecycleScope
import androidx.paging.PagingData
import androidx.recyclerview.widget.GridLayoutManager
import com.example.movieapp.R
import com.example.movieapp.databinding.FragmentMoviesCollectionBinding
import com.example.movieapp.domain.model.MoviesDomain
import com.example.movieapp.presentation.base.BaseFragment
import com.example.movieapp.presentation.ui.movie_details.MovieDetailFragment
import com.example.movieapp.presentation.ui.movies_collection.adapter.LoaderStateAdapter
import com.example.movieapp.presentation.ui.movies_collection.adapter.MoviesAdapter
import com.example.movieapp.presentation.ui.movies_collection.adapter.OnItemClickListener
import com.example.movieapp.presentation.ui.movies_collection.movies_state.MoviesState
import com.example.movieapp.presentation.ui.movies_collection.viewmodel.MoviesCollectionViewModel
import com.example.movieapp.util.Constants.BUNDLE_KEY_FOR_MOVIE_DETAILS
import com.example.movieapp.util.Constants.DATA_REFRESHING_TIME
import com.example.movieapp.util.Network
import com.example.movieapp.util.NetworkState
import com.example.movieapp.util.extensions.dialog.createDialog
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
    private val loadStateAdapter by lazy {
        LoaderStateAdapter {
            showDialog(it)
        }
    }

    private var gridLayoutColumn = 2

    override fun onBindViewModel(viewModel: MoviesCollectionViewModel) {
        setUpRecyclerView(gridLayoutColumn)
        observeMovies(viewModel)
        observeInternetConnection(viewModel)
        binding.topRatedRadioButton.isChecked = true
        observeErrorLiveData(viewModel)
        getMovies(viewModel)
        setDataRefreshingListener(viewModel)
    }

    private fun observeInternetConnection(viewModel: MoviesCollectionViewModel) {
        val networkObserver = Network(requireContext())
        networkObserver.observe(viewLifecycleOwner) {
            when (it) {
                is NetworkState.Available -> {
                    if (viewModel.isShownInternetAvailability) {
                        showToast(getString(R.string.restored_internet_text))
                        moviesAdapter.retry()
                        viewModel.isShownInternetAvailability = false
                    }
                }
                is NetworkState.UnAvailable -> {
                    viewModel.isShownInternetAvailability = true
                    showToast(getString(R.string.internet_error_text))
                }
            }
        }
    }


    private fun observeErrorLiveData(viewModel: MoviesCollectionViewModel) {
        viewModel.errorLiveData.observe(viewLifecycleOwner) {
            isProgressBarVisible(false)
            if (viewModel.isSubmittedEmptyData) {
                submitPagingData(PagingData.empty())
            }
            showToast(it)
        }
    }

    private fun observeMovies(
        viewModel: MoviesCollectionViewModel,
        moviesState: MoviesState = MoviesState.TopRatedMoviesState,
    ) {
        isProgressBarVisible(true)
        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.getMovies(moviesState)?.observe(viewLifecycleOwner) {
                submitPagingData(it)
            }
        }
    }

    private fun getMovies(viewModel: MoviesCollectionViewModel) {
        binding.movieStateRadioGroup.setOnCheckedChangeListener { _, i ->
            when (i) {
                R.id.topRatedRadioButton -> {
                    viewModel.isSubmittedEmptyData = true
                    observeMovies(viewModel, MoviesState.TopRatedMoviesState)
                }

                R.id.popularRadioButton -> {
                    viewModel.isSubmittedEmptyData = true
                    observeMovies(viewModel, MoviesState.PopularMoviesState)
                }
                R.id.favoriteRadioButton -> {
                    viewModel.isSubmittedEmptyData = true
                    observeMovies(viewModel, MoviesState.FavouriteMoviesState)
                }
            }
        }
    }

    private fun setUpRecyclerView(gridLayoutColumn: Int) {
        with(binding.moviesRecyclerView) {
            layoutManager = GridLayoutManager(requireContext(), gridLayoutColumn)
            adapter = moviesAdapter.withLoadStateFooter(loadStateAdapter)
        }
    }

    private fun setDataRefreshingListener(viewModel: MoviesCollectionViewModel) {
        with(binding.moviesRefreshLayout) {
            setOnRefreshListener {
                viewModel.isSubmittedEmptyData = false
                viewLifecycleOwner.lifecycleScope.launch {
                    observeMovies(viewModel, viewModel.getMovieState)
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

    private fun submitPagingData(data: PagingData<MoviesDomain>) {
        viewLifecycleOwner.lifecycleScope.launch {
            isProgressBarVisible(false)
            moviesAdapter.submitData(data)
        }
    }

    private fun showDialog(text: String, action: () -> Unit = { moviesAdapter.retry() }) {
        requireContext().createDialog(text) {
            action.invoke()
        }
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