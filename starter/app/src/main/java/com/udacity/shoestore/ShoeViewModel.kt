package com.udacity.shoestore

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.udacity.shoestore.models.Shoe

class ShoeViewModel: ViewModel() {
    //
    // Constants
    //
    private val EMPTY_SHOE = Shoe("Name", 35.0, "Company", "", R.drawable.no_shoe_image_available)
    private val IMAGE_RESOURCES = listOf(R.drawable.no_shoe_image_available, R.drawable.new_balance_550_white)

    //
    // Members
    //
    private var _shoeList = MutableLiveData<MutableList<Shoe>>(mutableListOf())
    val shoeList: LiveData<MutableList<Shoe>>
        get() = _shoeList

    private var editShoeIndex: Int? = null

    private var _detailedShoe = MutableLiveData<Shoe>(EMPTY_SHOE.copy())
    val detailedShoe: LiveData<Shoe>
        get() = _detailedShoe

    //
    // onAction
    //
    fun onAddShoe() {
        val list = _shoeList.value ?: return

        editShoeIndex = list.size
        _detailedShoe.value = EMPTY_SHOE.copy()
    }

    fun onShoeSelected(index: Int) {
        val list = _shoeList.value ?: return

        editShoeIndex = index
        _detailedShoe.value = list[index].copy()
    }

    fun onShoeSaved() {
        val index = editShoeIndex ?: return
        val shoe = _detailedShoe.value ?: return
        val list = _shoeList.value ?: return

        list.setOrAdd(index, shoe)
        resetShoe()
    }

    fun onCancelEditShoe() {
        resetShoe()
    }

    private fun resetShoe() {
        editShoeIndex = null
        _detailedShoe.value = EMPTY_SHOE.copy()
    }

    //
    // Setters
    //

    fun setCompanyName(companyName: String) {
        _detailedShoe.value?.company = companyName
    }

    fun setName(name: String) {
        _detailedShoe.value?.name = name
    }

    fun setDescription(description: String) {
        _detailedShoe.value?.description = description
    }

    fun setShoeSize(size: Double) {
        _detailedShoe.value?.size = size
    }

    fun switchImage() {
        val shoe = _detailedShoe.value ?: return

        val currentIndex = IMAGE_RESOURCES.indexOf(shoe.image)
        val nextIndex = (currentIndex + 1) % IMAGE_RESOURCES.size

        _detailedShoe.value = shoe.copy(image = IMAGE_RESOURCES[nextIndex])
    }
}