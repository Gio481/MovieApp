package com.example.movieapp.presentation.ui.splash.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class SplashViewModel : ViewModel() {
    fun splash(action: () -> Unit) {
        viewModelScope.launch {
            delay(1000)
            action()
        }
    }
}