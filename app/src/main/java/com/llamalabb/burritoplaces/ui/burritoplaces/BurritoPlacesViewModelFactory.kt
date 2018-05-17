package com.llamalabb.burritoplaces.ui.burritoplaces

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider

class BurritoPlacesViewModelFactory(private val apiKey: String) : ViewModelProvider.NewInstanceFactory() {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return BurritoPlacesViewModel(apiKey) as T
    }
}