package com.example.movieapp.presentation.ui.movies

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.GridLayoutManager
import com.example.movieapp.R
import com.example.movieapp.databinding.FragmentMoviesBinding
import com.example.movieapp.domain.model.MoviesDomain
import com.example.movieapp.presentation.base.BaseFragment
import com.example.movieapp.presentation.ui.movie_details.MovieDetailsFragment
import com.example.movieapp.presentation.ui.movies.adapter.MoviesAdapter
import com.example.movieapp.presentation.ui.movies.adapter.OnItemClickListener
import com.example.movieapp.presentation.ui.movies.movies_state.MoviesState
import com.example.movieapp.presentation.ui.movies.viewmodel.MoviesViewModel
import com.example.movieapp.util.Constants.BUNDLE_KEY_FOR_DETAILS_FRAGMENT
import com.example.movieapp.util.extensions.fragment.showToast
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel

class MoviesFragment(private val moviesState: MoviesState) :
    BaseFragment<FragmentMoviesBinding, MoviesViewModel>(), OnItemClickListener<MoviesDomain> {

    override val bindingInflater: (inflater: LayoutInflater, container: ViewGroup?, attachToRoot: Boolean) -> FragmentMoviesBinding
        get() = FragmentMoviesBinding::inflate

    override val viewModel: MoviesViewModel by viewModel()

    private val detailsBottomSheet by lazy { MovieDetailsFragment() }

    private val myAdapter by lazy { MoviesAdapter(this) }

    override fun onBindViewModel(viewModel: MoviesViewModel) {
        detailsBottomSheet.setStyle(DialogFragment.STYLE_NORMAL, R.style.BottomSheetTheme)
        setUpRecyclerView()
        determineMoviesState(viewModel)
        observeMoviesLiveData()
        observeErrorLiveData()
    }

    private fun observeErrorLiveData() {
        viewModel.errorLiveData.observe(viewLifecycleOwner) {
            showToast(it)
        }
    }

    private fun observeMoviesLiveData() {
        viewModel.moviesLiveData.observe(viewLifecycleOwner) { flow ->
            lifecycleScope.launch {
                flow.collectLatest {
                    myAdapter.submitData(it)
                }
            }
        }
    }

    private fun determineMoviesState(viewModel: MoviesViewModel) {
        viewModel.getMoviesState(moviesState)
    }

    private fun setUpRecyclerView() {
        with(binding.moviesRecyclerView) {
            layoutManager = GridLayoutManager(requireContext(), 3)
            adapter = myAdapter
        }
    }

    override fun onItemClick(detail: MoviesDomain) {
        val bundle = bundle {
            putParcelable(BUNDLE_KEY_FOR_DETAILS_FRAGMENT, detail)
        }
        detailsBottomSheet.arguments = bundle
        detailsBottomSheet.show(childFragmentManager, null)
    }

    private fun bundle(block: Bundle.() -> Unit): Bundle {
        return Bundle().apply(block)
    }
}