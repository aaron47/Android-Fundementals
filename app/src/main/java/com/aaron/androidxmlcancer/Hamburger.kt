package com.aaron.androidxmlcancer
import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Hamburger(
    val meat: String,
    val toppings: String
) : Parcelable