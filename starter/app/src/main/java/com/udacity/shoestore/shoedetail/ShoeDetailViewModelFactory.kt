package com.udacity.shoestore.shoedetail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.udacity.shoestore.models.Shoe

class ShoeDetailViewModelFactory(private val shoe: Shoe) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(ShoeDetailViewModel::class.java)) {
            return ShoeDetailViewModel(shoe) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}