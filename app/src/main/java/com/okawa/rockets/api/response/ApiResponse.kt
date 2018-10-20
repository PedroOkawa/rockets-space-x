package com.okawa.rockets.api.response

import retrofit2.Response

@Suppress("unused")
sealed class ApiResponse<T> {

    companion object {
        fun <T> create(throwable: Throwable): ApiErrorResponse<T> {
            return ApiErrorResponse(throwable.message ?: "Something went wrong")
        }

        fun <T> create(response: Response<T>): ApiResponse<T> {
            return if(response.isSuccessful) {
                val body = response.body()
                ApiSuccessResponse(body)
            } else {
                val message = response.errorBody()?.string()
                val errorMessage = if(message.isNullOrEmpty()) {
                    response.message()
                } else {
                    message
                }
                ApiErrorResponse(errorMessage ?: "Something went wrong")
            }
        }
    }

}

data class ApiSuccessResponse<T>(val body: T?) : ApiResponse<T>()

data class ApiErrorResponse<T>(val errorMessage: String) : ApiResponse<T>()