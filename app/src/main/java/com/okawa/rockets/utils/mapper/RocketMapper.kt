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

    private fun convert(rocket: RocketResponse): RocketEntity {
        return RocketEntity(
            rocket.id,
            rocket.rocketId,
            rocket.rocketName,
            rocket.description,
            rocket.country,
            rocket.active,
            rocket.engines.number,
            rocket.images
        )
    }

}