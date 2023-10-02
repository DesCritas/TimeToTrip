package com.descritas.timetotrip.model

import com.descritas.timetotrip.dto.Flight


data class FlightModel(
    val flights: List<Flight> = emptyList(),

    val empty: Boolean = false,

    )

sealed interface FlightModelState {
    object Idle : FlightModelState
    object Refresh : FlightModelState

    object Loading : FlightModelState
    object Error : FlightModelState
}