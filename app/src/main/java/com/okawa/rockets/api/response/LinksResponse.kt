package com.okawa.rockets.api.response

import com.squareup.moshi.Json

data class LinksResponse(
    @Json(name = "mission_patch") val missionPatch: String? = ""
)