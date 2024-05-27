package com.udacity.shoestore

import android.text.Editable
import android.text.TextWatcher
import android.widget.EditText

fun <T> MutableList<T>.setOrAdd(index: Int, element: T) {
    if (index < size) {
        this[index] = element
    } else if (index == size) {
        add(element)
    } else {
        throw IndexOutOfBoundsException("Index: $index, Size: $size")
    }
}

fun EditText.addAfterTextChangedListener(afterTextChanged: (String) -> Unit) {
    this.addTextChangedListener(object : TextWatcher {
        override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            // Do nothing
        }

        override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            // Do nothing
        }

        override fun afterTextChanged(s: Editable?) {
            s?.let { afterTextChanged(it.toString()) }
        }
    })
}
