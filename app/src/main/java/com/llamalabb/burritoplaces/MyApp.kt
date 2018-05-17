package com.llamalabb.burritoplaces

import android.app.Application
import android.content.Context
import android.net.ConnectivityManager
import com.llamalabb.burritoplaces.data.InternetUtil
import pl.charmas.android.reactivelocation2.ReactiveLocationProvider

class MyApp : Application() {

    companion object {
        lateinit var locationProvider: ReactiveLocationProvider

    }

    override fun onCreate() {
        super.onCreate()
        locationProvider = ReactiveLocationProvider(this)
        InternetUtil.init(this)
    }

    fun isNetworkConnected(): Boolean {
        val connectivityManager = getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val activeNetworkInfo = connectivityManager.activeNetworkInfo
        return activeNetworkInfo != null && activeNetworkInfo.isConnected
    }
}