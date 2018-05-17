package com.llamalabb.burritoplaces.ui.burritoplaces


import android.Manifest
import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.llamalabb.burritoplaces.R
import com.llamalabb.burritoplaces.model.Place
import com.tbruyelle.rxpermissions2.RxPermissions
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.addTo
import kotlinx.android.synthetic.main.fragment_burrito_places.view.*




class BurritoPlacesFragment : Fragment() {

    lateinit var model: BurritoPlacesViewModel
    private val placesRecyclerAdapter = PlacesRecyclerAdapter()
    private val compositeDisposable = CompositeDisposable()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View {
        val view = inflater.inflate(R.layout.fragment_burrito_places, container, false)
        setupActionBar()
        setupRecyclerView(view.places_recycler)
        requestPermissions()
        return view
    }


    private fun requestPermissions(){
        val rxPermissions = RxPermissions(requireActivity())
        rxPermissions.requestEachCombined(Manifest.permission.ACCESS_COARSE_LOCATION,
                        Manifest.permission.ACCESS_FINE_LOCATION)
                .subscribe{ permission ->
                    if(permission.granted){
                        setupViewModel()
                        view?.permission_text?.visibility = View.GONE
                    } else {
                        view?.permission_text?.visibility = View.VISIBLE
                    }

                }.addTo(compositeDisposable)
    }


    private fun setupActionBar(){
        val appCompatActivity = activity as (AppCompatActivity)
        appCompatActivity.supportActionBar?.title = "Burrito Places"
        appCompatActivity.supportActionBar?.setDisplayHomeAsUpEnabled(false)
    }

    private fun setupRecyclerView(recyclerView: RecyclerView){
        recyclerView.adapter = placesRecyclerAdapter
        recyclerView.layoutManager = LinearLayoutManager(context)
    }

    private fun setupViewModel(){
        val apiKey = getString(R.string.maps_key)
        model = ViewModelProviders
                .of(this, BurritoPlacesViewModelFactory(apiKey))
                .get(BurritoPlacesViewModel::class.java)
        observeViewModel()
        observeLoading()
    }


    private fun observeViewModel(){
        model.getPlaces().observe(this, Observer<List<Place>> { places ->
            places?.let{ placesRecyclerAdapter.setPlaces(it) }
        })
    }

    private fun observeLoading(){
        model.getLoadingStatus().observe(this, Observer<Boolean>{ isLoading ->
            isLoading?.let{ view?.progressBar?.visibility = if(it) View.VISIBLE else View.GONE }
        })
    }

    override fun onStop() {
        super.onStop()
        compositeDisposable.dispose()
    }



}
