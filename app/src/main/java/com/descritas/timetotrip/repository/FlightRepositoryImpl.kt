package com.descritas.timetotrip.repository

import android.os.NetworkOnMainThreadException
import com.descritas.timetotrip.api.OkHttpApiClient
import com.descritas.timetotrip.api.RetrofitApiClient
import com.descritas.timetotrip.error.NetworkError
import com.descritas.timetotrip.error.UnknownError
import com.descritas.timetotrip.dto.Flight
import java.io.IOException

class FlightRepositoryImpl : FlightRepository {

    override suspend fun likeById(flight: Flight) {
        flight.liked = !flight.liked
    }

    override suspend fun getFlights(): List<Flight> {

        try {
            //val response = OkHttpApiClient.getFlights()
            val response = RetrofitApiClient.getFlightsRetrofit()

            val listOfFlights = mutableListOf<Flight>()

            response?.flights?.let { listOfFlights.addAll(it) }

            return listOfFlights
        } catch (e: IOException) {
            throw NetworkError
        } catch (e: NetworkOnMainThreadException) {
            throw NetworkError
        } catch (e: Exception) {
            throw UnknownError
        }
    }
}
