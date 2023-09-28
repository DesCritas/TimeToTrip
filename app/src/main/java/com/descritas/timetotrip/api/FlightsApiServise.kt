package com.descritas.timetotrip.api

import com.descritas.timetotrip.dto.Flight
import com.descritas.timetotrip.dto.FlightList
import com.google.gson.Gson
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.RequestBody.Companion.toRequestBody
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.POST


private const val BASE_URL =
    "https://vmeste.wildberries.ru/stream/api/avia-service/v1/suggests/getCheap"

private val MEDIA_TYPE_JSON = "application/json".toMediaType()

private val okhttp = OkHttpClient.Builder()
    .addInterceptor { chain ->
        val request = chain.request()
            .newBuilder()
            .addHeader("authority", "vmeste.wildberries.ru")
            .addHeader("accept", "application/json, text/plain, */*")
            .addHeader("cache-control", "no-cache")
            .addHeader("content-type", "application/json")
            .addHeader("origin", "https://vmeste.wildberries.ru")
            .addHeader("pragma", "no-cache")
            .addHeader("referer", "https://vmeste.wildberries.ru/avia")
            .addHeader("sec-fetch-dest", "empty")
            .addHeader("sec-fetch-mode", "cors")
            .addHeader("sec-fetch-site", "same-origin")
            .post(("{\"startLocationCode\":\"LED\"}").toRequestBody(MEDIA_TYPE_JSON))
            .build()
        chain.proceed(request)
    }
    .build()


//private val retrofit = Retrofit.Builder()
//    .addConverterFactory(GsonConverterFactory.create())
//    .baseUrl(BASE_URL)
//    .client(okhttp)
//    .build()

//interface FlightsApiService {
//    @POST()
//    suspend fun getFlights(): Response<FlightList>
//}
interface FlightsApiService {
    @POST("suggests/getCheap")
    @Headers(
        "authority: vmeste.wildberries.ru",
        "accept: application/json, text/plain, */*",
        "cache-control: no-cache",
        "content-type: application/json",
        "origin: https://vmeste.wildberries.ru",
        "pragma: no-cache",
        "referer: https://vmeste.wildberries.ru/avia",
        "sec-fetch-dest: empty",
        "sec-fetch-mode: cors",
        "sec-fetch-site: same-origin"
    )
    suspend fun getCheapFlights(@Body request: CheapFlightsRequest): Response<FlightList>
}
data class CheapFlightsRequest(val body: String)

object FlightsApi {
    private val okhttp = OkHttpClient.Builder()
        .addInterceptor { chain ->
            val original = chain.request()
            val request = original.newBuilder()
                .method(original.method, original.body)
                .build()
            chain.proceed(request)
        }
        .build()

    private val retrofit = Retrofit.Builder()
        .client(okhttp)
        .baseUrl("https://vmeste.wildberries.ru/stream/api/avia-service/v1/")
        .build()

    val retrofitService: FlightsApiService by lazy {
        retrofit.create(FlightsApiService::class.java)
    }
}

