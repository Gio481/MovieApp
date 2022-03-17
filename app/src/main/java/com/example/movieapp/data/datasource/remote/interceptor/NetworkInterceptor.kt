package com.example.movieapp.data.datasource.remote.interceptor

import com.example.movieapp.util.NetworkException
import okhttp3.Interceptor
import okhttp3.Response

class NetworkInterceptor:Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        if(!isConnected()){
            throw NetworkException()
        }
        return chain.proceed(chain.request().newBuilder().build())
    }

    private fun isConnected(): Boolean {
        return try {
            Runtime.getRuntime().exec(PING).waitFor() == PING_TIME
        } catch (e: Exception) {
            false
        }
    }

    companion object {
        private const val PING = "ping -c 1 google.com"
        private const val PING_TIME = 0
    }
}