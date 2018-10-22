package com.okawa.rockets.db.manager

import android.arch.lifecycle.LiveData
import android.arch.paging.DataSource
import com.okawa.rockets.api.response.RocketResponse
import com.okawa.rockets.db.dao.RocketDao
import com.okawa.rockets.db.entity.RocketEntity
import com.okawa.rockets.utils.mapper.RocketMapper
import javax.inject.Inject

class RocketDBManager @Inject constructor(
    private val rocketDao: RocketDao,
    private val rocketMapper: RocketMapper
) {

    fun storeRocket(rocket: RocketResponse?) {
        rocket?.let {
            rocketDao.insert(rocketMapper.convert(rocket))
        }
    }

    fun storeRockets(rockets: List<RocketResponse>?) {
        rockets?.let {
            rocketDao.insertAll(rocketMapper.convert(rockets))
        }
    }

    fun retrieveRocket(rocketId: String?): LiveData<RocketEntity> {
        return rocketDao.loadRocket(rocketId)
    }

    fun retrieveRockets(filterByActive: Boolean?): DataSource.Factory<Int, RocketEntity> {
        return if(filterByActive == true) rocketDao.loadActiveRockets() else rocketDao.loadRockets()
    }

}