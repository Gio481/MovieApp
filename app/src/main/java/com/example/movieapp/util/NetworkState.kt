package com.example.movieapp.util

sealed class NetworkState {
    object Available : NetworkState()
    object UnAvailable : NetworkState()
}
