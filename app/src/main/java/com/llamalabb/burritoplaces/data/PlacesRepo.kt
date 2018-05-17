package com.llamalabb.burritoplaces.data

import android.location.Location
import com.llamalabb.burritoplaces.PlacesParameters
import com.llamalabb.burritoplaces.model.PlacesApiResponse
import io.reactivex.Observable
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

object PlacesRepo {
    private const val MAPS_API_URL = "https://maps.googleapis.com/"
    private var placesRetrofitInstance = Retrofit.Builder()
            .baseUrl(MAPS_API_URL)
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    private val placesApiService = placesRetrofitInstance.create(PlacesApiService::class.java)

    fun getNearbyBurritoPlaces(apiKey: String, location: Location) : Observable<PlacesApiResponse>{
        val lat = location.latitude.toString()
        val lng = location.longitude.toString()
        val placesParameters = PlacesParameters(apiKey, lat, lng)
                .rankByDistance()
                .addKeyword("burrito")
                .addType("food")
                .openNow()
        return placesApiService.getPlaces(placesParameters)
    }
}