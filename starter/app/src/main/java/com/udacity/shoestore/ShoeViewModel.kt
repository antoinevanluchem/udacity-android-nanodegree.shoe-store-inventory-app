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

    private var newShoe: Shoe = Shoe("Name", 0.0, "Company", "", R.drawable.no_shoe_image_available)
    private var editShoeIndex: Int? = null


    val detailedShoe: Shoe
        get() = editShoeIndex?.let { _shoeList.value?.get(it) } ?: newShoe

    fun setIndexOfDetailedShoe(index: Int) {
        editShoeIndex = index
    }


    fun setCompanyName(companyName: String) {
        detailedShoe.company = companyName
    }

    fun onSaveShoeClicked() {
        if (editShoeIndex != null) {
            _shoeList.value?.set(editShoeIndex!!, detailedShoe)
        } else {
            _shoeList.value?.add(detailedShoe.copy())
        }

        editShoeIndex = null
        newShoe = Shoe("Name", 0.0, "Company", "", R.drawable.no_shoe_image_available)

        Log.i("ShoeViewModel", _shoeList.value.toString())
    }
}