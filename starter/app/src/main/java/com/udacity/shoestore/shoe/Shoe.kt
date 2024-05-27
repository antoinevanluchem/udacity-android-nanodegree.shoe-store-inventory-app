package com.udacity.shoestore.shoe

import android.os.Parcelable
import com.udacity.shoestore.R
import kotlinx.parcelize.Parcelize

// Use Float instead of Double, because for shoes-sizes we don't need the extra precision
@Parcelize
data class Shoe(
    var name: String, var size: Float, var company: String, var description: String,
    var image: Int = R.drawable.no_shoe_image_available) : Parcelable