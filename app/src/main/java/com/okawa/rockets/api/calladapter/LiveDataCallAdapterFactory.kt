package com.okawa.rockets.api.calladapter

import android.arch.lifecycle.LiveData
import com.okawa.rockets.api.response.ApiResponse
import retrofit2.CallAdapter
import retrofit2.Retrofit
import java.lang.reflect.ParameterizedType
import java.lang.reflect.Type

class LiveDataCallAdapterFactory : CallAdapter.Factory() {

    override fun get(returnType: Type?, annotations: Array<out Annotation>?, retrofit: Retrofit?): CallAdapter<*, *>? {
        if(CallAdapter.Factory.getRawType(returnType) != LiveData::class.java) {
            return null
        }

        val observableType = CallAdapter.Factory.getParameterUpperBound(0, returnType as ParameterizedType)
        val rawObservableType = CallAdapter.Factory.getRawType(observableType)
        if(rawObservableType != ApiResponse::class.java) {
            throw IllegalArgumentException("Type must be a resource")
        }
        if(observableType !is ParameterizedType) {
            throw IllegalArgumentException("Resource must be parameterized!")
        }
        val bodyType = CallAdapter.Factory.getParameterUpperBound(0, observableType)
        return LiveDataCallAdapter<Any>(bodyType)
    }

}