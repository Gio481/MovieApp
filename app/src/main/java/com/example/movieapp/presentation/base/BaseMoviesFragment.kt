package com.example.movieapp.presentation.base

import android.os.Bundle
import android.view.View
import androidx.lifecycle.ViewModel
import androidx.viewbinding.ViewBinding

abstract class BaseMoviesFragment<VB : ViewBinding, VM : ViewModel> : BaseFragment<VB>() {

    abstract val viewModel: VM

    abstract fun onBindViewModel(viewModel: VM)

    override fun initial() {
        onBindViewModel(viewModel)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        onBindViewModel(viewModel)
    }

}