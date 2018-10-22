package com.okawa.rockets.repository.launch

import android.arch.lifecycle.LiveData
import android.arch.paging.LivePagedListBuilder
import android.arch.paging.PagedList
import com.okawa.rockets.api.response.ApiResponse
import com.okawa.rockets.api.response.LaunchResponse
import com.okawa.rockets.api.service.ApiService
import com.okawa.rockets.data.NetworkBoundResource
import com.okawa.rockets.data.Result
import com.okawa.rockets.db.entity.LaunchEntity
import com.okawa.rockets.db.manager.LaunchDBManager
import com.okawa.rockets.utils.AppExecutors
import javax.inject.Inject

class LaunchRepositoryImpl @Inject constructor(
    private val apiService: ApiService,
    private val appExecutors: AppExecutors,
    private val launchDBManager: LaunchDBManager
): LaunchRepository {

    override fun getLaunchesByRocketId(rocketId: String?): LiveData<Result<List<LaunchEntity>>> {
        return object: NetworkBoundResource<List<LaunchEntity>, List<LaunchResponse>>(appExecutors) {
            override fun saveCallResult(data: List<LaunchResponse>?) {
                launchDBManager.storeLaunches(data)
            }

            override fun loadFromDatabase(): LiveData<List<LaunchEntity>> {
                return launchDBManager.retrieveLaunches(rocketId)
            }

            override fun createCall(): LiveData<ApiResponse<List<LaunchResponse>>> {
                return apiService.getLaunches()
            }

            override fun shouldRequestFromNetwork(data: List<LaunchEntity>?): Boolean {
                return data == null || data.isEmpty()
            }

        }.asLiveData()
    }

}