package com.okawa.rockets.api.response

import com.squareup.moshi.Json
import java.util.*

data class LaunchResponse(
    @Json(name = "flight_number") val flightNumber: Long,
    @Json(name = "mission_name") val missionName: String,
    @Json(name = "launch_date_utc") val launchDate: Date,
    @Json(name = "launch_success") val launchSuccess: Boolean? = false,
    val rocket: RocketResponse?,
    val links: LinksResponse?
)