package com.example.movieapp

import android.app.Application
import com.example.movieapp.di.databaseModule
import com.example.movieapp.di.networkModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin
import org.koin.dsl.module

class App : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@App)
            module {
                networkModule
                databaseModule
            }
        }
    }
}