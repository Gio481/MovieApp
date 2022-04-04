package com.example.movieapp.presentation.ui.splash

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.movieapp.R
import com.example.movieapp.databinding.FragmentSplashBinding
import com.example.movieapp.presentation.base.BaseFragment
import com.example.movieapp.presentation.ui.splash.viewmodel.SplashViewModel
import kotlin.reflect.KClass

class SplashFragment : BaseFragment<FragmentSplashBinding, SplashViewModel>() {

    override val bindingInflater: (inflater: LayoutInflater, container: ViewGroup?, attachToRoot: Boolean) -> FragmentSplashBinding
        get() = FragmentSplashBinding::inflate

    override fun getViewModelClass(): KClass<SplashViewModel> = SplashViewModel::class


    override fun onBindViewModel(viewModel: SplashViewModel) {
        viewModel.splash {
            findNavController().navigate(R.id.action_splashFragment_to_moviesCollectionFragment)
        }
    }

}