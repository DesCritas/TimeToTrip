package com.descritas.timetotrip.repository

import android.os.NetworkOnMainThreadException
import com.descritas.timetotrip.api.OkHttpApiClient
import com.descritas.timetotrip.error.NetworkError
import com.descritas.timetotrip.error.UnknownError
import com.descritas.timetotrip.dto.Flight
import java.io.IOException

class FlightRepositoryImpl(): FlightRepository {
    private var flights = emptyList<Flight>()

    override suspend fun likeById(flight: Flight) {
        flight.liked = !flight.liked
    }

    override suspend fun getFlights():List<Flight> {

        try {
            val response = OkHttpApiClient.getFlights()
            //val response = ArrayList<Flight>()
            //flights.add(Flight("SVO1", "FRA1", "2023-09-01", "2023-09-01", "2056", "","",liked = false))
            //flights.add(Flight("SVO2", "FRA2", "2023-09-01", "2023-09-01", "2056", "","",liked = false))
            //flights.add(Flight("SVO3", "FRA3", "2023-09-01", "2023-09-01", "2056", "","",liked = false))
            //flights.add(Flight("SVO4", "FRA4", "2023-09-01", "2023-09-01", "2056", "","",liked = false))
            //flights.add(Flight("SVO5", "FRA5", "2023-09-01", "2023-09-01", "2056", "","",liked = false))



            val listOfFlights = mutableListOf<Flight>()

            response?.flights?.let { listOfFlights.addAll(it) }

            return listOfFlights
        } catch (e: IOException) {
            throw NetworkError
        } catch (e: NetworkOnMainThreadException){
            throw NetworkError
        } catch (e: Exception) {
            throw UnknownError
        }
    }
}