package com.okawa.rockets.api.service

import android.arch.lifecycle.LiveData
import com.okawa.rockets.api.response.ApiResponse
import com.okawa.rockets.api.response.LaunchResponse
import com.okawa.rockets.api.response.RocketResponse
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiService {

    @GET("launches")
    fun getLaunches(): LiveData<ApiResponse<List<LaunchResponse>>>

    @GET("rockets")
    fun getRockets(): LiveData<ApiResponse<List<RocketResponse>>>

    @GET("rockets/{rocketId}")
    fun getRocketById(@Path("rocketId") rocketId: String): LiveData<ApiResponse<RocketResponse>>

}