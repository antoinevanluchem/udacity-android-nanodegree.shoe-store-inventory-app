package com.udacity.shoestore.shoelist

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.udacity.shoestore.models.Shoe

class ShoeListViewModel: ViewModel() {
    var _shoeList = MutableLiveData<MutableList<Shoe>>()
    val shoeList: LiveData<MutableList<Shoe>>
        get() = _shoeList

    init {

        Log.i("ShoeListViewModel", "Init ShoeListViewModel")

        _shoeList.value = mutableListOf()
    }

    fun addShoe(newShoe: Shoe) {
        _shoeList.value?.add(newShoe)
    }
}