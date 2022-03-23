package com.example.movieapp.presentation.ui.splash

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.movieapp.R
import com.example.movieapp.databinding.FragmentSplashBinding
import com.example.movieapp.presentation.base.BaseFragment
import org.koin.androidx.viewmodel.ext.android.viewModel

class SplashFragment : BaseFragment<FragmentSplashBinding, SplashScreenViewModel>() {
    override val bindingInflater: (inflater: LayoutInflater, container: ViewGroup?, attachToRoot: Boolean) -> FragmentSplashBinding
        get() = FragmentSplashBinding::inflate

    override val viewModel: SplashScreenViewModel by viewModel()

    override fun onBindViewModel(viewModel: SplashScreenViewModel) {
        viewModel.splashScreenDelay()
        findNavController().navigate(R.id.action_splashFragment_to_moviesCollectionFragment)
    }
}