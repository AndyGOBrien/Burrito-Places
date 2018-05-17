package com.llamalabb.burritoplaces.ui.burritoplaces

import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.findNavController
import com.llamalabb.burritoplaces.R
import com.llamalabb.burritoplaces.databinding.ItemPlaceBinding
import com.llamalabb.burritoplaces.model.Place

class PlacesRecyclerAdapter : RecyclerView.Adapter<PlacesRecyclerAdapter.PlaceViewHolder>() {
    private var places : List<Place> = listOf()

    fun setPlaces(newList: List<Place>){
        if(places.isEmpty()){
            places = newList
            notifyItemRangeInserted(0, newList.size)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PlaceViewHolder {
        val binding: ItemPlaceBinding = DataBindingUtil
                .inflate(LayoutInflater.from(parent.context), R.layout.item_place, parent, false)
        return PlaceViewHolder(binding)
    }


    override fun onBindViewHolder(holder: PlaceViewHolder, position: Int) {
        holder.binding.place = places[position]
        holder.binding.placeCard.setOnClickListener{
            val args = Bundle()
            args.putSerializable("place", places[position])
            it.findNavController().navigate(R.id.pinnedMapFragment, args)
        }
    }

    override fun getItemCount() = places.size

    class PlaceViewHolder(val binding: ItemPlaceBinding) : RecyclerView.ViewHolder(binding.root)
}