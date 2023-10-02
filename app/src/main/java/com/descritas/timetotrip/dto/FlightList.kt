package com.descritas.timetotrip.dto

import com.google.gson.annotations.SerializedName

data class FlightList(
    @SerializedName("flights")
    val flights: List<Flight>
)