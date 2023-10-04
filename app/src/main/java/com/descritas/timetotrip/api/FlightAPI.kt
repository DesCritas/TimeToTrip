package com.descritas.timetotrip.api

import com.descritas.timetotrip.dto.FlightList
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.RequestBody
import okhttp3.RequestBody.Companion.toRequestBody
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Body
import retrofit2.http.Headers
import retrofit2.http.POST

val requestBody = "{\"startLocationCode\":\"LED\"}".toRequestBody("application/json".toMediaType())

interface ApiClientRetrofit {
    @Headers(
        "authority: vmeste.wildberries.ru",
        "accept: application/json, text/plain, */*",
        "cache-control: no-cache",
        "Content-Type: application/json",
        "origin: https://vmeste.wildberries.ru",
        "pragma: no-cache",
        "referer: https://vmeste.wildberries.ru/avia",
        "sec-fetch-dest: empty",
        "sec-fetch-mode: cors",
        "sec-fetch-site: same-origin"
    )
    @POST("stream/api/avia-service/v1/suggests/getCheap")
    suspend fun getFlightsRetrofit(@Body request: RequestBody = requestBody): FlightList?
}

object RetrofitApiClient : ApiClientRetrofit {
    private val api: ApiClientRetrofit
    private const val BASE_URL = "https://vmeste.wildberries.ru/"

    init {
        val client = OkHttpClient.Builder()
            .build()

        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(client)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        api = retrofit.create(ApiClientRetrofit::class.java)
    }

    override suspend fun getFlightsRetrofit(request: RequestBody): FlightList? = withContext(Dispatchers.IO) {
        //val requestBody = "{\"startLocationCode\":\"LED\"}".toRequestBody("application/json".toMediaType())

        try {
            return@withContext api.getFlightsRetrofit(requestBody)
        } catch (e: Exception) {
            e.printStackTrace()
        }
        return@withContext null
    }


}
