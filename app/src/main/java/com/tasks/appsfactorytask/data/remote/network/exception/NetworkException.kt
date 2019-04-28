package com.tasks.appsfactorytask.data.remote.network.exception

import com.tasks.appsfactorytask.base.domain.exception.AppsFactoryException
import retrofit2.Response
import java.io.IOException

object NetworkException {
    fun httpError(response: Response<Any>?): AppsFactoryException {
        val message: String? = null
        var responseBody: String? = null
        var statusCode = 0
        val errorCode = 0
        response?.let { statusCode = it.code() }
        response?.let {
            responseBody = it.errorBody()?.string()
            try {
                // in case of handle http API error
            } catch (exception: Exception) {
            }
        }

        var kind = AppsFactoryException.Kind.HTTP
        when (statusCode) {
            500 -> kind = AppsFactoryException.Kind.SERVER_DOWN
            408 -> kind = AppsFactoryException.Kind.TIME_OUT
            401 -> kind = AppsFactoryException.Kind.UNAUTHORIZED
        }

        return AppsFactoryException(kind, message?.let { message }
            ?: kotlin.run { "" })
            .setErrorCode(errorCode)
            .setStatusCode(statusCode)
            .setData(responseBody)
    }

    fun networkError(exception: IOException): AppsFactoryException {
        return AppsFactoryException(AppsFactoryException.Kind.NETWORK, exception)
    }

    fun unexpectedError(exception: Throwable): AppsFactoryException {
        return AppsFactoryException(AppsFactoryException.Kind.UNEXPECTED, exception)
    }


}