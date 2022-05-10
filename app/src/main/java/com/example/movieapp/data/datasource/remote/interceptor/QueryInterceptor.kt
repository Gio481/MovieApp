package com.example.movieapp.data.datasource.remote.interceptor

import okhttp3.Interceptor
import okhttp3.Response

class QueryInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val url = chain.request().url.newBuilder().addQueryParameter(QUERY, API_KEY).build()
        val request = chain.request().newBuilder().url(url).build()
        return chain.proceed(request)
    }

    companion object {
        private const val QUERY = "api_key"
        private const val API_KEY = "956b4bd6a9f961e0d1a837c9de42714b"
    }
}