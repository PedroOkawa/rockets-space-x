package com.okawa.rockets.utils.mapper

import com.okawa.rockets.api.response.LaunchResponse
import com.okawa.rockets.db.entity.LaunchEntity
import javax.inject.Inject

class LaunchMapper @Inject constructor() {

    companion object {
        private const val INVALID_ROCKET_ID = "invalid_rocket_id"
    }

    fun convert(launches: List<LaunchResponse>) : List<LaunchEntity> {
        return launches.map { launch ->
            convert(launch)
        }
    }

    private fun convert(launch: LaunchResponse): LaunchEntity {
        val rocketId = launch.rocket?.rocketId ?: INVALID_ROCKET_ID
        val missionPatch = launch.links?.missionPatch ?: ""
        return LaunchEntity(
            launch.flightNumber,
            launch.missionName,
            launch.launchDate,
            launch.launchSuccess,
            missionPatch,
            rocketId
        )
    }

}