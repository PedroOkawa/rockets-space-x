package com.okawa.rockets.repository

import android.arch.lifecycle.LiveData
import android.arch.paging.LivePagedListBuilder
import android.arch.paging.PagedList
import com.okawa.rockets.api.response.ApiResponse
import com.okawa.rockets.api.response.RocketResponse
import com.okawa.rockets.api.service.ApiService
import com.okawa.rockets.data.NetworkBoundResource
import com.okawa.rockets.data.Result
import com.okawa.rockets.db.entity.RocketEntity
import com.okawa.rockets.db.manager.RocketDBManager
import com.okawa.rockets.utils.AppExecutors
import javax.inject.Inject

class RocketRepositoryImpl @Inject constructor(
    private val apiService: ApiService,
    private val appExecutors: AppExecutors,
    private val rocketDBManager: RocketDBManager
): RocketRepository {

    companion object {
        private const val PAGE_SIZE = 20
    }

    override fun getRockets(): LiveData<Result<PagedList<RocketEntity>>> {
        return object: NetworkBoundResource<PagedList<RocketEntity>, List<RocketResponse>>(appExecutors) {
            override fun saveCallResult(data: List<RocketResponse>?) {
                rocketDBManager.storeRockets(data)
            }

            override fun loadFromDatabase(): LiveData<PagedList<RocketEntity>> {
                return LivePagedListBuilder(rocketDBManager.retrieveAllRockets(), PAGE_SIZE).build()
            }

            override fun createCall(): LiveData<ApiResponse<List<RocketResponse>>> {
                return apiService.getRockets()
            }

        }.asLiveData()
    }

}