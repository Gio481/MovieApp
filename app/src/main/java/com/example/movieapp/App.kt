package com.example.movieapp

import android.app.Application
import com.example.movieapp.di.databaseModule
import com.example.movieapp.di.networkModule
import com.example.movieapp.di.repositoryModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class App : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@App)
            modules(
                listOf(
                    networkModule,
                    databaseModule,
                    repositoryModule
                )
            )
        }
    }
}