package com.okawa.rockets.repository

import android.arch.lifecycle.LiveData
import android.arch.paging.PagedList
import com.okawa.rockets.data.Result
import com.okawa.rockets.db.entity.RocketEntity

interface RocketRepository {

    fun getRockets(filterByActive: Boolean?): LiveData<Result<PagedList<RocketEntity>>>

}