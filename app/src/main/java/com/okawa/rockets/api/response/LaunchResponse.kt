package com.okawa.rockets.api.response

import com.squareup.moshi.Json
import java.util.*

data class LaunchResponse(
    @Json(name = "flight_number") val flightNumber: Long,
    @Json(name = "launch_date_utc") val launchDate: Date,
    val rocket: RocketResponse
)