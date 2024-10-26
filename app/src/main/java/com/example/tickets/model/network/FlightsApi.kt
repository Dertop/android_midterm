package com.example.tickets.model.network

import com.example.tickets.model.entity.Offer
import retrofit2.Call
import retrofit2.http.GET

interface FlightsApi {

    @GET("fake-api-demo/offer_list")
    suspend fun getFlights(): List<Offer>

}