package com.udacity.shoestore

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.udacity.shoestore.models.Shoe

class ShoeViewModel: ViewModel() {
    private var _shoeList = MutableLiveData<MutableList<Shoe>>(mutableListOf())
    val shoeList: LiveData<MutableList<Shoe>>
        get() = _shoeList

    private var editShoeIndex: Int? = null

    fun setIndexOfDetailedShoe(index: Int) {
        editShoeIndex = index
    }

    fun getDetailedShoe(): Shoe {
        return if (editShoeIndex == null) {
            Shoe("Name", 0.0, "Company", "", R.drawable.no_shoe_image_available)
        } else {
            _shoeList.value!![editShoeIndex!!]
        }
    }

    fun saveShoe(companyName: String) {
        if (editShoeIndex == null) {
            val newShoe = Shoe("Name", 0.0, companyName, "", R.drawable.no_shoe_image_available)
            _shoeList.value?.add(newShoe)
        } else {
            _shoeList.value?.get(editShoeIndex!!)?.company = companyName
            editShoeIndex = null
        }
    }

}