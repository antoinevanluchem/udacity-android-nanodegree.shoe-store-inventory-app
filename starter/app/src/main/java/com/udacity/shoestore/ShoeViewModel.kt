package com.udacity.shoestore

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.udacity.shoestore.models.Shoe

class ShoeViewModel: ViewModel() {
    private var _shoeList = MutableLiveData<MutableList<Shoe>>(mutableListOf())
    val shoeList: LiveData<MutableList<Shoe>>
        get() = _shoeList

    private var editShoeIndex: Int? = null
    var detailedShoe: Shoe = Shoe("Name", 0.0, "Company", "", R.drawable.no_shoe_image_available)

    fun setCompanyName(companyName: String) {
        detailedShoe.company = companyName
    }

    fun onAddShoeClicked() {
        editShoeIndex = _shoeList.value!!.size
        detailedShoe = Shoe("Name", 0.0, "Company", "", R.drawable.no_shoe_image_available)
    }

    fun onShoeSelected(index: Int) {
        editShoeIndex = index
        detailedShoe = _shoeList.value!![index].copy()
    }

    fun onShoeSaved() {
        _shoeList.value!!.setOrAdd(editShoeIndex!!, detailedShoe)
    }

    fun onEditShoeCancelled() {
        editShoeIndex = null
        detailedShoe = Shoe("Name", 0.0, "Company", "", R.drawable.no_shoe_image_available)
    }
}

fun <T> MutableList<T>.setOrAdd(index: Int, element: T) {
    if (index < size) {
        this[index] = element
    } else if (index == size) {
        add(element)
    } else {
        throw IndexOutOfBoundsException("Index: $index, Size: $size")
    }
}