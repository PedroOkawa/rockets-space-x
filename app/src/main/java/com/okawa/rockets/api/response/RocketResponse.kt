package com.okawa.rockets.api.response

import com.squareup.moshi.Json
import java.util.*

data class RocketResponse(
    @Json(name = "rocket_id") val rocketId: String,
    @Json(name = "rocket_name") val rocketName: String,
    val description: String = "",
    val country: String = "",
    val active: Boolean = false,
    val engines: EngineResponse? = null,
    @Json(name = "flickr_images") val images: List<String> = Collections.emptyList()
)