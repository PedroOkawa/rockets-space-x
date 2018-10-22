package com.okawa.rockets.utils.mapper

import com.okawa.rockets.api.response.RocketResponse
import com.okawa.rockets.db.entity.RocketEntity
import javax.inject.Inject

class RocketMapper @Inject constructor() {

    fun convert(rockets: List<RocketResponse>) : List<RocketEntity> {
        return rockets.map { rocket ->
            convert(rocket)
        }
    }

    fun convert(rocket: RocketResponse): RocketEntity {
        val enginesCount = rocket.engines?.number ?: 0
        return RocketEntity(
            rocket.rocketId,
            rocket.rocketName,
            rocket.description,
            rocket.country,
            rocket.active,
            enginesCount,
            rocket.images
        )
    }

}