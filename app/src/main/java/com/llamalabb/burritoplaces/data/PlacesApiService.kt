package com.llamalabb.burritoplaces.data

import com.llamalabb.burritoplaces.model.PlacesApiResponse
import io.reactivex.Observable
import retrofit2.http.*

interface PlacesApiService {
    @GET("/maps/api/place/nearbysearch/json")
    fun getPlaces(@QueryMap parameters: Map<String, String>) : Observable<PlacesApiResponse>
}