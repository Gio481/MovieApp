package com.example.movieapp.di

import com.example.movieapp.data.datasource.remote.MoviesApiService
import com.example.movieapp.data.datasource.remote.interceptor.QueryInterceptor
import okhttp3.OkHttpClient
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

private const val BASE_URL = "https://api.themoviedb.org/3/"

private fun provideUserApi(retrofit: Retrofit): MoviesApiService {
    return retrofit.create(MoviesApiService::class.java)
}

private fun provideGsonConvertorFactory(): GsonConverterFactory = GsonConverterFactory.create()

private fun provideHttpClient(): OkHttpClient {
    return OkHttpClient.Builder()
        .addInterceptor(QueryInterceptor()).build()
}

private fun provideRetrofit(): Retrofit {
    return retrofit {
        baseUrl(BASE_URL)
        client(provideHttpClient())
        addConverterFactory(provideGsonConvertorFactory())
    }
}

private fun retrofit(block: Retrofit.Builder.() -> Unit): Retrofit {
    return Retrofit.Builder().apply(block).build()
}

val networkModule = module {
    single { provideUserApi(get()) }
    single { provideHttpClient() }
    single { provideGsonConvertorFactory() }
    single { provideRetrofit() }
}