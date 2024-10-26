package com.example.tickets.model.entity

import com.google.gson.annotations.SerializedName

data class Location(
    @SerializedName("city_name") val cityName: String,
    @SerializedName("code") val code: String
)