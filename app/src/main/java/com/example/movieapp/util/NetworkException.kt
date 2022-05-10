package com.example.movieapp.util

import okio.IOException

class NetworkException : IOException() {
    override val message: String = ERROR_MESSAGE

    companion object {
        private const val ERROR_MESSAGE = "No Internet Connection!"
    }
}