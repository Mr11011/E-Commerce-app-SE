package com.example.buywzme.data

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Product(
    val id: String,
    val name: String,
    val category: String,
    val price: Float,
    val offerPercentage: Float?,
    val description: String?,
    val colors: List<Int>? = null,
    val sizes: List<String>? = null,
    val images: List<String>
) : Parcelable {
    constructor():this("0","","",0f,0f,"",colors= emptyList(),sizes= emptyList(),images= emptyList())



}