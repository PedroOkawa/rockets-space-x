package com.okawa.rockets.repository.rocket

import android.arch.lifecycle.LiveData
import android.arch.paging.PagedList
import com.okawa.rockets.data.Result
import com.okawa.rockets.db.entity.RocketEntity

interface RocketRepository {

    fun getRocket(rocketId: String): LiveData<Result<RocketEntity>>

    fun getRockets(filterByActive: Boolean?): LiveData<Result<PagedList<RocketEntity>>>

}