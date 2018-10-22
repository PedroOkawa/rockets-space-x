package com.okawa.rockets.utils.mapper

import com.okawa.rockets.api.response.LaunchResponse
import com.okawa.rockets.db.entity.LaunchEntity
import javax.inject.Inject

class LaunchMapper @Inject constructor() {

    fun convert(launches: List<LaunchResponse>) : List<LaunchEntity> {
        return launches.map { launch ->
            convert(launch)
        }
    }

    private fun convert(launch: LaunchResponse): LaunchEntity {
        return LaunchEntity(
            launch.flightNumber,
            launch.rocket.rocketId,
            launch.launchDate
        )
    }

}