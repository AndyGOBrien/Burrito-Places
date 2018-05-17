package com.llamalabb.burritoplaces.ui.burritoplaces

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import android.location.Location
import com.llamalabb.burritoplaces.data.InternetUtil
import com.llamalabb.burritoplaces.data.LocationUtil
import com.llamalabb.burritoplaces.data.PlacesRepo
import com.llamalabb.burritoplaces.model.Place
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.addTo
import io.reactivex.schedulers.Schedulers

class BurritoPlacesViewModel(private val apiString: String) : ViewModel() {
    private val places = MutableLiveData<List<Place>>()
    private val compositeDisposable = CompositeDisposable()
    private val isLoading = MutableLiveData<Boolean>()

    init{
        isLoading.value = true
        checkLocation()
    }

    private fun checkLocation(){
        LocationUtil.getUpdatedLocation().subscribe { location ->
            if(InternetUtil.isInternetOn()){
                getPlacesFromApi(location)
            }
        }.addTo(compositeDisposable)
    }

    private fun getPlacesFromApi(location: Location){
        PlacesRepo.getNearbyBurritoPlaces(apiString, location)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe { response ->
                    isLoading.value = false
                    places.postValue(response.places)
                }.addTo(compositeDisposable)
    }

    fun getLoadingStatus() : MutableLiveData<Boolean>{
        return isLoading
    }

    fun getPlaces() : MutableLiveData<List<Place>> {
        return places
    }

    override fun onCleared() {
        compositeDisposable.dispose()
        super.onCleared()
    }
}