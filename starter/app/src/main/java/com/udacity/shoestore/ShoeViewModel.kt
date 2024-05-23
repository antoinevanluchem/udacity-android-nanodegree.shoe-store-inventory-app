package com.udacity.shoestore

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.udacity.shoestore.models.Shoe

class ShoeViewModel: ViewModel() {
    private val EMPTY_SHOE = Shoe("Name", 0.0, "Company", "", R.drawable.no_shoe_image_available)

    private var _shoeList = MutableLiveData<MutableList<Shoe>>(mutableListOf())
    val shoeList: LiveData<MutableList<Shoe>>
        get() = _shoeList

    private var editShoeIndex: Int? = null

    private var _detailedShoe = MutableLiveData<Shoe>(EMPTY_SHOE.copy())
    val detailedShoe: LiveData<Shoe>
        get() = _detailedShoe

    fun onAddShoe() {
        _shoeList.value?.let {
            editShoeIndex = it.size
            _detailedShoe.value = EMPTY_SHOE.copy()
        }
    }

    fun onShoeSelected(index: Int) {
        _shoeList.value?.let {
            editShoeIndex = index
            _detailedShoe.value = it[index].copy()
        }
    }

    fun setCompanyName(companyName: String) {
        _detailedShoe.value?.company = companyName
    }

    fun setName(name: String) {
        _detailedShoe.value?.name = name
    }

    fun setShoeSize(size: Double) {
        _detailedShoe.value?.size = size
    }

    fun onShoeSaved() {
        editShoeIndex?.let { index ->
            detailedShoe.value?.let {shoe ->
                _shoeList.value?.setOrAdd(index, shoe)
            }
        }

        resetShoe()
    }

    fun onCancelEditShoe() {
        resetShoe()
    }

    private fun resetShoe() {
        editShoeIndex = null
        _detailedShoe.value = EMPTY_SHOE.copy()
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