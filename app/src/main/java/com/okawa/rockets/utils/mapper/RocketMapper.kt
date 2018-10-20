package com.okawa.rockets.utils.mapper

import com.okawa.rockets.api.response.RocketResponse
import com.okawa.rockets.db.entity.RocketEntity
import javax.inject.Inject

class RocketMapper @Inject constructor() {

    fun convertToDB(rocket: RocketResponse) : RocketEntity {
        return RocketEntity(rocket.id,
            rocket.rocketId,
            rocket.rocketName,
            rocket.description,
            rocket.engines.number)
    }

    fun convertToDB(rockets: List<RocketResponse>) : List<RocketEntity> {
        val result = ArrayList<RocketEntity>()
        rockets.forEach { rocket ->
            result.add(convertToDB(rocket))
        }
        return result
    }

}