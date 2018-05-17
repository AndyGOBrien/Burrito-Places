package com.llamalabb.burritoplaces.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import java.io.Serializable


data class PlacesApiResponse(
        @SerializedName("html_attributions") @Expose var htmlAttributions: List<Any> = listOf(),
        @SerializedName("results") @Expose var places: List<Place> = listOf(),
        @SerializedName("status") @Expose var status: String = "",
        @SerializedName("next_page_token") @Expose var nextPageToken: String? = null
)

data class Place(
        @SerializedName("geometry") @Expose var geometry: Geometry = Geometry(),
        @SerializedName("icon") @Expose var icon: String = "",
        @SerializedName("id") @Expose var id: String = "",
        @SerializedName("name") @Expose var name: String = "",
        @SerializedName("opening_hours") @Expose var openingHours: OpeningHours = OpeningHours(),
        @SerializedName("photos") @Expose var photos: List<Photo> = listOf(),
        @SerializedName("place_id") @Expose var placeId: String = "",
        @SerializedName("price_level") @Expose var priceLevel: Int = 0,
        @SerializedName("rating") @Expose var rating: Double = 0.0,
        @SerializedName("reference") @Expose var reference: String = "",
        @SerializedName("scope") @Expose var scope: String = "",
        @SerializedName("types") @Expose var types: List<String> = listOf(),
        @SerializedName("vicinity") @Expose var vicinity: String = ""
) : Serializable {
    fun getFormattedPriceLevel() : String{
        return when(priceLevel){
            0 -> "•  $"
            1 -> "•  $$"
            2 -> "•  $$$"
            3 -> "•  $$$$"
            4 -> "•  $$$$$"
            else -> ""
        }
    }

    fun getRating() : Float {
        return rating.toFloat()
    }
}

data class Geometry(
        @SerializedName("location") @Expose var location: Location = Location(),
        @SerializedName("viewport") @Expose var viewport: Viewport = Viewport()
) : Serializable

data class Location(
        @SerializedName("lat") @Expose var lat: Double = 0.0,
        @SerializedName("lng") @Expose var lng: Double = 0.0
) : Serializable

data class Viewport(
        @SerializedName("northeast") @Expose var northeast: Northeast = Northeast(),
        @SerializedName("southwest") @Expose var southwest: Southwest = Southwest()
) : Serializable

data class Southwest(
        @SerializedName("lat") @Expose var lat: Double = 0.0,
        @SerializedName("lng") @Expose var lng: Double = 0.0
) : Serializable

data class Northeast(
        @SerializedName("lat") @Expose var lat: Double = 0.0,
        @SerializedName("lng") @Expose var lng: Double = 0.0
) : Serializable

data class Photo(
        @SerializedName("height") @Expose var height: Int = 0,
        @SerializedName("html_attributions") @Expose var htmlAttributions: List<String> = listOf(),
        @SerializedName("photo_reference") @Expose var photoReference: String = "",
        @SerializedName("width") @Expose var width: Int = 0
) : Serializable

data class OpeningHours(
        @SerializedName("open_now") @Expose var openNow: Boolean = false,
        @SerializedName("weekday_text") @Expose var weekdayText: List<Any> = listOf()
) : Serializable