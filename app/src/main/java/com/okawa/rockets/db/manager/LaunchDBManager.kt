package com.okawa.rockets.db.manager

import android.arch.lifecycle.LiveData
import android.arch.paging.DataSource
import com.okawa.rockets.api.response.LaunchResponse
import com.okawa.rockets.db.dao.LaunchDao
import com.okawa.rockets.db.entity.LaunchEntity
import com.okawa.rockets.utils.mapper.LaunchMapper
import javax.inject.Inject

class LaunchDBManager @Inject constructor(
    private val launchDao: LaunchDao,
    private val launchMapper: LaunchMapper
) {

    fun storeLaunches(launches: List<LaunchResponse>?) {
        launches?.let {
            launchDao.insertAll(launchMapper.convert(launches))
        }
    }

    fun retrieveLaunches(rocketId: String?): LiveData<List<LaunchEntity>> {
        return launchDao.loadLaunchesByRocketId(rocketId)
    }

}