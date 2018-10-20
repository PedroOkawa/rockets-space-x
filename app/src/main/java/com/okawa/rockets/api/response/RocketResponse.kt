package com.okawa.rockets.api.response

import com.squareup.moshi.Json

data class RocketResponse(
    val id: Long,
    @Json(name = "rocket_id") val rocketId: String,
    @Json(name = "rocket_name") val rocketName: String,
    val description: String,
    val country: String,
    val active: Boolean,
    val engines: EngineResponse,
    @Json(name = "flickr_images") val images: List<String>
)