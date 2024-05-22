package com.udacity.shoestore.shoedetail

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.udacity.shoestore.models.Shoe

class ShoeDetailViewModel(shoe: Shoe): ViewModel() {

    private val _shoe = MutableLiveData<Shoe>()
    val shoe: LiveData<Shoe>
        get() = _shoe
    init {
        _shoe.value = shoe
    }
    fun setCompanyName(companyName: String) {
        _shoe.value?.company = companyName
    }
}