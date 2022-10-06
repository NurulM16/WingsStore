package com.gemslight.common.base

import okhttp3.ResponseBody

sealed class AppResponse<T : Any?>(val state: Int = 2, val data: (T)?) {
    companion object {
        fun <T> success(t: T) = ResponseSuccess(t)
        fun <T> error(error: Exception?, body: ResponseBody?, code: Int) =
            ResponseError<T>(error, body, code)

        fun <T> loading() = ResponseLoading<T>()
        const val SUCCESS = 0
        const val ERROR = 1
        const val LOADING = 2
    }
}

data class ResponseSuccess<T>(val d: T) : AppResponse<T>(0, d)
data class ResponseError<T>(val error: Exception?, val body: ResponseBody?, val code: Int) :
    AppResponse<T>(1, null)

class ResponseLoading<T> : AppResponse<T>(2, null)