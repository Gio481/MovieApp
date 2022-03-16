package com.example.movieapp.presentation.ui.splash

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.example.movieapp.databinding.FragmentSplashBinding
import com.example.movieapp.presentation.base.BaseFragment

class SplashFragment : BaseFragment<FragmentSplashBinding, SplashScreenViewModel>() {
    override val bindingInflater: (inflater: LayoutInflater, container: ViewGroup?, attachToRoot: Boolean) -> FragmentSplashBinding
        get() = FragmentSplashBinding::inflate

    override val viewModel: SplashScreenViewModel by viewModels()

    override fun onBindViewModel(viewModel: SplashScreenViewModel) {}
}