package com.example.movieapp.presentation.ui.splash

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class SplashScreenViewModel : ViewModel() {
    fun splashScreenDelay() {
        viewModelScope.launch {
            delay(DELAY)
        }
    }

    companion object {
        private const val DELAY = 1000L
    }
}