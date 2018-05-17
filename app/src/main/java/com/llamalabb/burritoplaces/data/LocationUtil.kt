package com.llamalabb.burritoplaces.data

import android.annotation.SuppressLint
import android.location.Location
import com.google.android.gms.location.LocationRequest
import com.llamalabb.burritoplaces.MyApp
import io.reactivex.Observable

object LocationUtil {
    @SuppressLint("MissingPermission")
    fun getLastKnownLocation() = MyApp.locationProvider.lastKnownLocation
    @SuppressLint("MissingPermission")
    fun getUpdatedLocation() : Observable<Location> {
        val request = LocationRequest.create()
                .setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY)
                .setNumUpdates(5)
                .setInterval(1000)
        return MyApp.locationProvider.getUpdatedLocation(request)
    }
}