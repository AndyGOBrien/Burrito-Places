package com.llamalabb.burritoplaces



class PlacesParameters(
        key: String,
        latitude: String,
        longitude: String
) : HashMap<String, String>(){
    init {
        this["key"] = key
        this["location"] = "$latitude,$longitude"
    }

    fun rankByDistance() : PlacesParameters{
        this["rankby"] = "distance"
        return this
    }

    fun rankByProminence(): PlacesParameters{
        this["rankby"] = "prominence"
        return this
    }

    fun openNow() : PlacesParameters{
        this["opennow"] = "true"
        return this
    }

    fun addKeyword(keyword: String) : PlacesParameters {
        this["keyword"] = keyword
        return this
    }

    fun addName(name: String) : PlacesParameters {
        this["title"] = name
        return this
    }

    fun addType(type: String) : PlacesParameters {
        this["type"] = type
        return this
    }

    fun pagetoken(token: String) : PlacesParameters {
        this["pagetoken"] = token
        return this
    }
    fun minPrice(priceType: PriceType) : PlacesParameters {
        this["minprice"] = priceType.type
        return this
    }
    fun maxPrice(priceType: PriceType) : PlacesParameters {
        this["maxprice"] = priceType.type
        return this
    }
}