package com.descritas.timetotrip.api

import com.descritas.timetotrip.dto.FlightList
import com.google.gson.Gson
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import okhttp3.Call
import okhttp3.Callback
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.RequestBody.Companion.toRequestBody
import okhttp3.Response
import java.io.IOException

interface ApiClient {
    suspend fun getFlights():FlightList?
}

object OkHttpApiClient : ApiClient {
    private val client = OkHttpClient()
    private val gson = Gson()
    private const val url = "https://vmeste.wildberries.ru/stream/api/avia-service/v1/suggests/getCheap"
    private val mediaType = "application/json".toMediaType()
    private val requestBody = "{\"startLocationCode\":\"LED\"}".toRequestBody(mediaType)
    override suspend fun getFlights():FlightList?= withContext(Dispatchers.IO){
        val request = Request.Builder()
            .url(url)
            .addHeader("authority", "vmeste.wildberries.ru")
            .addHeader("accept", "application/json, text/plain, */*")
            .addHeader("cache-control", "no-cache")
            .addHeader("Content-Type", "application/json")
            .addHeader("origin", "https://vmeste.wildberries.ru")
            .addHeader("pragma", "no-cache")
            .addHeader("referer", "https://vmeste.wildberries.ru/avia")
            .addHeader("sec-fetch-dest", "empty")
            .addHeader("sec-fetch-mode", "cors")
            .addHeader("sec-fetch-site", "same-origin")
            .post(requestBody)
            .build()
        println(request)

        try {
            val response: Response = client.newCall(request).execute()
            val responseBody: String? = response.body?.string()
            return@withContext gson.fromJson(responseBody, FlightList::class.java)
        } catch (e: Exception) {
            e.printStackTrace()
        }
        return@withContext null



        //lateinit var flightList: FlightList
        //return runCatching {
        //    val response: Response = client.newCall(request).execute()
        //    val responseBody: String? = response.body?.string()
        //    flightList = gson.fromJson(responseBody, FlightList::class.java)
        //    flightList
        //}.getOrElse {
        //    flightList
        //}

        //client.newCall(request).enqueue(object : Callback {
        //    override fun onFailure(call: Call, e: IOException) {
        //        println(e.message.toString())
        //    }
        //    override fun onResponse(call: Call, response: Response) {
        //        println(response.toString())
        //        val responseBody = response.body?.string() ?: ""
        //        flightList = gson.fromJson(responseBody, FlightList::class.java)
        //        response.close()
        //    }
        //})

    }
}

