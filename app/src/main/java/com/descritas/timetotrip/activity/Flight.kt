package com.descritas.timetotrip.activity


import android.os.Parcel
import android.os.Parcelable
import java.io.Serializable



data class Flight(
    val departure: String,
    val arrival: String,
    val startDate: String,
    val endDate: String,
    val price: String,
    val liked: Boolean
): Serializable

