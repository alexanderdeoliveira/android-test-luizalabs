package com.android_test_luizalabs.core.extensions

import retrofit2.HttpException
import java.net.ConnectException
import java.net.SocketException
import java.net.SocketTimeoutException
import java.net.UnknownHostException
import java.util.concurrent.TimeoutException

internal fun Throwable.toRequestThrowable(): Throwable {
    return when (this) {
        is HttpException,
        is UnknownHostException,
        is TimeoutException,
        is InterruptedException,
        is SocketTimeoutException,
        is SocketException,
        is ConnectException -> getDefaultThrowable()

        else -> this
    }
}

internal fun getDefaultThrowable() = NetworkException()


private const val CONNECTION_ERROR_MESSAGE = "Verifique sua conexão e tente novamente."

class NetworkException(override val message: String = CONNECTION_ERROR_MESSAGE) : Exception()

