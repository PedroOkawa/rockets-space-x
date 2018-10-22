package com.okawa.rockets.data

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MediatorLiveData
import android.support.annotation.MainThread
import android.support.annotation.WorkerThread
import com.okawa.rockets.api.response.ApiErrorResponse
import com.okawa.rockets.api.response.ApiResponse
import com.okawa.rockets.api.response.ApiSuccessResponse
import com.okawa.rockets.utils.executors.AppExecutors

abstract class NetworkBoundResource<ResultType, RequestType>
@MainThread constructor(private val appExecutors: AppExecutors) {

    private val result = MediatorLiveData<Result<ResultType>>()

    init {
        result.value = Result.loading(null)
        val databaseSource = loadFromDatabase()
        result.addSource(databaseSource) { data ->
            result.removeSource(databaseSource)
            if(shouldRequestFromNetwork(data)) {
                requestFromNetwork(databaseSource)
            } else {
                result.addSource(databaseSource) {newData ->
                    setValue(Result.success(newData))
                }
            }
        }
    }

    private fun requestFromNetwork(databaseSource: LiveData<ResultType>) {
        val apiResponse = createCall()

        result.addSource(databaseSource) { newData ->
            setValue(Result.loading(newData))
        }

        result.addSource(apiResponse) { response ->
            result.removeSource(apiResponse)
            result.removeSource(databaseSource)

            when(response) {
                is ApiSuccessResponse -> {
                    appExecutors.getDiskIO().execute {
                        saveCallResult(processResponse(response))
                        appExecutors.getMainThread().execute {
                            result.addSource(loadFromDatabase()) { newData ->
                                setValue(Result.success(newData))
                            }
                        }
                    }
                }
                is ApiErrorResponse -> {
                    result.addSource(databaseSource) { newData ->
                        setValue(Result.error(response.errorMessage, newData))
                    }
                }
            }
        }
    }

    fun asLiveData() = result as LiveData<Result<ResultType>>

    @MainThread
    private fun setValue(newValue: Result<ResultType>) {
        if(result.value != newValue) {
            result.value = newValue
        }
    }

    @WorkerThread
    protected open fun processResponse(response: ApiSuccessResponse<RequestType>) = response.body

    @WorkerThread
    protected abstract fun saveCallResult(data: RequestType?)

    @MainThread
    protected open fun shouldRequestFromNetwork(data: ResultType?) = true

    @MainThread
    protected abstract fun loadFromDatabase(): LiveData<ResultType>

    @MainThread
    protected abstract fun createCall(): LiveData<ApiResponse<RequestType>>

}