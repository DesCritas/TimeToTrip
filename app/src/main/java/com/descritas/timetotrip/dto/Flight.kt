package com.descritas.timetotrip.dto

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class Flight(
    @SerializedName("startLocationCode")
    val departure: String,
    @SerializedName("endLocationCode")
    val arrival: String,
    @SerializedName("startDate")
    val startDate: String,
    @SerializedName("endDate")
    val endDate: String,
    @SerializedName("price")
    val price: String,
    @SerializedName("startCity")
    val startCity: String,
    @SerializedName("endCity")
    val endCity: String,

    var liked: Boolean
): Serializable

