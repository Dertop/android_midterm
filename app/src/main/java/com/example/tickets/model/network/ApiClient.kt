package com.example.tickets.model.network

import com.example.tickets.model.entity.Offer
import retrofit2.Retrofit
import retrofit2.awaitResponse
import retrofit2.converter.gson.GsonConverterFactory

object ApiClient {

    const val BASE_URL = "https://my-json-server.typicode.com/estharossa/"

    private val retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    private val flightsApi: FlightsApi = retrofit.create(FlightsApi::class.java)

    suspend fun getFlights(): List<Offer> {
        return try {
            val response = flightsApi.getFlights()
            response
        } catch (e: Exception) {
            e.printStackTrace()
            emptyList()
        }
    }

}