package com.example.movieapp.presentation.ui.splash

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.movieapp.R
import com.example.movieapp.databinding.FragmentSplashBinding
import com.example.movieapp.presentation.base.BaseFragment
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class SplashFragment : BaseFragment<FragmentSplashBinding>() {
    override val bindingInflater: (inflater: LayoutInflater, container: ViewGroup?, attachToRoot: Boolean) -> FragmentSplashBinding
        get() = FragmentSplashBinding::inflate

    override fun initial() {
        lifecycleScope.launch {
            delay(1000)
            findNavController().navigate(R.id.action_splashFragment_to_moviesCollectionFragment)
        }
    }

}