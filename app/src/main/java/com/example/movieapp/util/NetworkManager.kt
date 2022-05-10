package com.example.movieapp.util

import android.content.Context
import android.net.ConnectivityManager
import android.net.Network
import android.net.NetworkCapabilities
import android.net.NetworkRequest
import androidx.lifecycle.LiveData
import java.net.URL

class NetworkManager(context: Context) : LiveData<NetworkState?>() {
    private lateinit var callback: ConnectivityManager.NetworkCallback
    private val network: MutableSet<Network> = mutableSetOf()
    private val connectivityManager =
        context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager

    private fun validNetworkChecker() {
        if (network.size > 0) {
            postValue(NetworkState.Available)
        } else {
            postValue(NetworkState.UnAvailable)
        }
    }

    override fun onInactive() {
        connectivityManager.unregisterNetworkCallback(callback)
    }

    private fun networkChecker() =
        object : ConnectivityManager.NetworkCallback() {
            override fun onAvailable(network: Network) {
                val networkCapabilities = connectivityManager.getNetworkCapabilities(network)
                val hasInternetConnectivityManager =
                    networkCapabilities?.hasCapability(NetworkCapabilities.NET_CAPABILITY_INTERNET)
                if (hasInternetConnectivityManager == true) {
                    try {
                        network.openConnection(URL(INTERNET_CHECKER_URL)).connect()
                        this@NetworkManager.network.add(network)
                        validNetworkChecker()
                    } catch (e: Exception) {
                        validNetworkChecker()
                    }
                }
            }

            override fun onLost(network: Network) {
                super.onLost(network)
                postValue(NetworkState.UnAvailable)
            }
        }

    override fun onActive() {
        super.onActive()
        callback = networkChecker()
        val networkRequest =
            NetworkRequest.Builder().addCapability(NetworkCapabilities.NET_CAPABILITY_INTERNET)
                .build()
        connectivityManager.registerNetworkCallback(networkRequest, callback)
    }

    companion object {
        private const val INTERNET_CHECKER_URL = "https://www.google.com/"
    }
}