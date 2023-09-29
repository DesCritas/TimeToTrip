package com.descritas.timetotrip.repository

import com.descritas.timetotrip.dto.Flight

interface FlightRepository {

    suspend fun likeById(flight: Flight)
    suspend fun getFlights():List<Flight>
}