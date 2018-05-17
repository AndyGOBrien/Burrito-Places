package com.llamalabb.burritoplaces.ui.map


import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.MapView
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.model.BitmapDescriptorFactory
import com.google.android.gms.maps.model.LatLng
import com.llamalabb.burritoplaces.R
import com.llamalabb.burritoplaces.model.Place
import kotlinx.android.synthetic.main.fragment_pinned_map.view.*
import com.google.android.gms.maps.model.MarkerOptions

class PinnedMapFragment : Fragment(), OnMapReadyCallback{

    lateinit var mapView: MapView
    lateinit var map: GoogleMap
    lateinit var place: Place

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        place = arguments?.getSerializable("place") as Place
    }


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_pinned_map, container, false)
        setupActionBar()
        setDescriptiveViews(view)
        mapView = view.map_view
        mapView.onCreate(savedInstanceState)
        mapView.getMapAsync(this)

        return view
    }

    private fun setupActionBar(){
        val appCompatActivity = activity as (AppCompatActivity)
        appCompatActivity.supportActionBar?.setDisplayHomeAsUpEnabled(true)
        appCompatActivity.supportActionBar?.title = place.name
    }


    private fun setDescriptiveViews(view: View){
        view.map_address_text.text = place.vicinity
        view.map_description_text.text = place.getFormattedPriceLevel()
        view.map_ratingBar.rating = place.getRating()
    }

    override fun onMapReady(googleMap: GoogleMap) {
        val lat = place.geometry.location.lat
        val lng = place.geometry.location.lng
        val latLng = LatLng(lat,lng)
        map = googleMap
        map.uiSettings.isMyLocationButtonEnabled = false

        addMarker(latLng)
        zoomCamera(latLng)

    }

    private fun addMarker(latLng: LatLng){
        map.addMarker(MarkerOptions()
                .position(latLng)
                .title(place.name))
                .setIcon(BitmapDescriptorFactory.fromResource(R.drawable.pin))
    }

    private fun zoomCamera(latLng: LatLng){
        val cameraUpdate = CameraUpdateFactory.newLatLngZoom(latLng, 16f)
        map.moveCamera(cameraUpdate)
    }


    override fun onResume() {
        mapView.onResume()
        super.onResume()

    }

    override fun onPause() {
        mapView.onPause()
        super.onPause()
    }

    override fun onDestroy() {
        mapView.onDestroy()
        super.onDestroy()
    }

    override fun onLowMemory() {
        mapView.onLowMemory()
        super.onLowMemory()
    }

}
