package com.udacity.shoestore.shoe

import android.os.Parcelable
import com.udacity.shoestore.R
import kotlinx.parcelize.Parcelize

@Parcelize
data class Shoe(
    var name: String, var size: Double, var company: String, var description: String,
    var image: Int = R.drawable.no_shoe_image_available) : Parcelable