package com.example.movieapp.presentation.ui.splash.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class SplashScreenViewModel : ViewModel() {
    fun splashScreenDelay(action: () -> Unit) {
        viewModelScope.launch {
            delay(DELAY)
            action.invoke()
        }
    }

    companion object {
        private const val DELAY = 1000L
    }
}