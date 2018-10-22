package com.okawa.rockets.repository.launch

import android.arch.lifecycle.LiveData
import android.arch.paging.PagedList
import com.okawa.rockets.data.Result
import com.okawa.rockets.db.entity.LaunchEntity

interface LaunchRepository {

    fun getLaunchesByRocketId(rocketId: String?): LiveData<Result<PagedList<LaunchEntity>>>

}