package com.udacity.shoestore

fun <T> MutableList<T>.setOrAdd(index: Int, element: T) {
    if (index < size) {
        this[index] = element
    } else if (index == size) {
        add(element)
    } else {
        throw IndexOutOfBoundsException("Index: $index, Size: $size")
    }
}