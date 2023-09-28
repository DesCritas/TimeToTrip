package com.descritas.timetotrip.repository

import android.os.NetworkOnMainThreadException
import com.descritas.timetotrip.api.OkHttpApiClient
import com.descritas.timetotrip.error.NetworkError
import com.descritas.timetotrip.error.UnknownError
import com.descritas.timetotrip.dto.Flight
import java.io.IOException

class FlightRepositoryImpl(): FlightRepository {

    override suspend fun likeByIdAsync(flight: Flight) {
        TODO("Not yet implemented")
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




            //val response = FlightsApi.retrofitService.getFlights()
            //if (!response.isSuccessful) {
            //    throw ApiError(response.code(), response.message())
            //}
            //val body = response.body() ?: throw ApiError(response.code(), response.message())
            ////postDao.insert(body.toEntity())
//
            //val responseBody = response.body?.string() ?: ""
            //flightList = gson.fromJson(responseBody, FlightList::class.java)
            val listOfFlights = mutableListOf<Flight>()
            //listOfFlights.addAll(body.flights)
            response?.flights?.let { listOfFlights.addAll(it) }
            //listOfFlights.addAll(response)
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